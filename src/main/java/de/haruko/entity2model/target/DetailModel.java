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

public class DetailModel {
    private String id;
    private ReferenzModel referenz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReferenzModel getReferenz() {
        return referenz;
    }

    public void setReferenz(ReferenzModel referenz) {
        this.referenz = referenz;
    }
    
}
