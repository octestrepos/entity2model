/**
 * $HeadURL: svn://172.16.24.126/trunk/de.orgacard/internal_110/de.orgacard.common/src/main/java/de/orgacard/common/utilities/ModelMapper.java $
 * $LastChangedRevision: 29472 $
 * $LastChangedDate: 2018-02-01 17:01:12 +0100 (Do, 01 Feb 2018) $
 * $Author: s.hahn $
 * Created on 01.02.2018, 06:17:56
 * ===========================================================================
 * Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
 */
package de.haruko.entity2model.process;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class ModelMapper {

    public static Object mapping(Object src, Object target) {
        if (src == null) {
            throw new IllegalArgumentException("usage: src object is missing");
        }
        if (target == null) {
            throw new IllegalArgumentException("usage: target object is missing");
        }

        try {
            if (!(src instanceof Collection)) {
                return mapTo(src, target);
            }
            return mapToCollection(src, target);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static Object mapTo(Object src, Object target) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (src != null) {
            BeanInfo beanInfo = Introspector.getBeanInfo(src.getClass());
            BeanInfo beanTarget = Introspector.getBeanInfo(target.getClass());
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                if (pd.getReadMethod() != null
                        && !"class".equals(pd.getName())) {
                    for (PropertyDescriptor pdTarget : beanTarget.getPropertyDescriptors()) {
                        if (pdTarget.getWriteMethod() != null
                                && !"class".equals(pdTarget.getName())
                                && pd.getDisplayName().equals(pdTarget.getDisplayName())) {
                            if (!pd.getPropertyType().equals(pdTarget.getPropertyType())) {
                                mapObject(src, target, pd, pdTarget);
                            } else if (pd.getReadMethod().invoke(src) instanceof Collection) {
                                mapCollection(src, target, pd, pdTarget);
                            } else {
                                mapSimple(src, target, pd, pdTarget);
                            }
                        }
                    }
                }
            }
        }

        return target;
    }

    private static void mapObject(Object src, Object target, PropertyDescriptor pdSrc, PropertyDescriptor pdTarget) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException {
        try {
            if (pdSrc.getReadMethod().invoke(src) != null) {
                Class targetClass = pdTarget.getPropertyType();
                Object targetObj = targetClass.newInstance();
                pdTarget.getWriteMethod().invoke(target, targetObj);
                mapTo(pdSrc.getReadMethod().invoke(src), targetObj);
            }
        } catch (java.lang.IllegalArgumentException ex) {
            System.out.println("Source " + pdSrc.getDisplayName() + " -> Target " + pdTarget.getDisplayName());
            throw ex;
        }
    }

    private static void mapCollection(Object src, Object target, PropertyDescriptor pdSrc, PropertyDescriptor pdTarget) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException {
        Class srcCollectionClass = pdSrc.getReadMethod().invoke(src).getClass();
        Class targetGenericsClass = getGenericsClass(pdTarget);
        Object targetCollection = srcCollectionClass.newInstance();
        for (Object o : (Collection) pdSrc.getReadMethod().invoke(src)) {
            ((Collection) targetCollection).add(mapTo(o, targetGenericsClass.newInstance()));
        }
        pdTarget.getWriteMethod().invoke(target, targetCollection);

    }

    private static void mapSimple(Object src, Object target, PropertyDescriptor pdSrc, PropertyDescriptor pdTarget) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException {
        try {
            pdTarget.getWriteMethod().invoke(target, pdSrc.getReadMethod().invoke(src));
        } catch (IllegalArgumentException ex) {
            System.out.println("Source " + pdSrc.getDisplayName() + " -> Target " + pdTarget.getDisplayName());
            throw ex;
        }
    }

    private static Object mapToCollection(Object src, Object target) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Object srcValue : (Collection) src) {
            Class targetClass = target.getClass();
            Object targetObj = targetClass.newInstance();
            ((Collection) target).add(mapTo(srcValue, targetObj));
        }
        return target;
    }
    
    private static Class<?> getGenericsClass(PropertyDescriptor pd) {
        ParameterizedType srcType = (ParameterizedType) pd.getReadMethod().getGenericReturnType();
        Class<?> genericsClass = (Class<?>) srcType.getActualTypeArguments()[0];
        return genericsClass;
    }

}
