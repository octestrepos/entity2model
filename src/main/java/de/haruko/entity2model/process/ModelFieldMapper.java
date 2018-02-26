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

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class ModelFieldMapper {

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
            Field[] srcFields = src.getClass().getDeclaredFields();
            Field[] targetFields = target.getClass().getDeclaredFields();

            for (Field srcField : srcFields) {
                for (Field targetField : targetFields) {
                    targetField.setAccessible(true);
                    srcField.setAccessible(true);
                    if (srcField.getName().equals(targetField.getName())
                            && srcField.get(src) != null) {
                        if (!srcField.getType().equals(targetField.getType())) {
                            mapObject(src, target, srcField, targetField);
                        } else if (srcField.get(src) instanceof Collection) {
                            mapCollection(src, target, srcField, targetField);
                        } else {
                            mapSimple(src, target, srcField, targetField);
                        }
                    }
                }
            }
        }

        return target;
    }

    private static void mapObject(Object src, Object target, Field srcField, Field targetField) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException {
        try {
            Object targetObj = targetField.getType().newInstance();
            targetField.set(target, targetObj);
            mapTo(srcField.get(src), targetObj);
        } catch (java.lang.IllegalArgumentException ex) {
            System.out.println("Source " + srcField.getName() + " -> Target " + targetField.getName());
            throw ex;
        }
    }

    private static void mapCollection(Object src, Object target, Field pdSrc, Field pdTarget) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException {
        Class srcCollectionClass = pdSrc.get(src).getClass();
        Class targetGenericsClass = (Class<?>) ((ParameterizedType)pdTarget.getGenericType()).getActualTypeArguments()[0];
        Object targetCollection = srcCollectionClass.newInstance();
        for (Object o : (Collection) pdSrc.get(src)) {
            ((Collection) targetCollection).add(mapTo(o, targetGenericsClass.newInstance()));
        }
        pdTarget.set(target, targetCollection);

    }

    private static void mapSimple(Object src, Object target, Field pdSrc, Field pdTarget) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException {
        try {
            pdTarget.set(target, pdSrc.get(src));
        } catch (IllegalArgumentException ex) {
            System.out.println("Source " + pdSrc.getName() + " -> Target " + pdTarget.getName());
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

}
