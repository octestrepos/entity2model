/**
* $HeadURL$
* $LastChangedRevision$
* $LastChangedDate$
* $Author$
* Created on 30.01.2018, 20:29:37
* ===========================================================================
* Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
*/

package de.haruko.entity2model.target;

import com.googlecode.jmapper.annotations.JMap;


public class DetailModel {
    @JMap
    private String id;
    @JMap("${referenz.id}")
    private String referenzId;
    @JMap("${referenz.name}")
    private String referenzName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenzId() {
        return referenzId;
    }

    public void setReferenzId(String referenzId) {
        this.referenzId = referenzId;
    }

    public String getReferenzName() {
        return referenzName;
    }

    public void setReferenzName(String referenzName) {
        this.referenzName = referenzName;
    }
    
    
}
