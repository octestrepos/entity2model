/**
 * $HeadURL$
 * $LastChangedRevision$
 * $LastChangedDate$
 * $Author$
 * Created on 30.01.2018, 20:06:52
 * ===========================================================================
 * Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
 */
package de.haruko.entity2model.entity;

public class Referenz {

    private String id;
    private String name;

    public Referenz() {
    }

    public Referenz(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Referenz setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Referenz setName(String name) {
        this.name = name;
        return this;
    }
}
