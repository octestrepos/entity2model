/**
 * $HeadURL$
 * $LastChangedRevision$
 * $LastChangedDate$
 * $Author$
 * Created on 30.01.2018, 20:06:52
 * ===========================================================================
 * Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
 */
package de.haruko.entity2model.target;

public class ReferenzModel {

    private String id;
    private String name;

    public ReferenzModel() {
    }

    public ReferenzModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public ReferenzModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReferenzModel setName(String name) {
        this.name = name;
        return this;
    }
}
