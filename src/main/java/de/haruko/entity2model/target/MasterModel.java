/**
* $HeadURL$
* $LastChangedRevision$
* $LastChangedDate$
* $Author$
* Created on 30.01.2018, 20:29:05
* ===========================================================================
* Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
*/

package de.haruko.entity2model.target;

import com.googlecode.jmapper.annotations.JMap;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class MasterModel {
    @JMap
    private String id;
    @JMap
    private Date datum;
    @JMap
    private BigDecimal zahl;
    @JMap("details")
    private List<DetailModel> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public BigDecimal getZahl() {
        return zahl;
    }

    public void setZahl(BigDecimal zahl) {
        this.zahl = zahl;
    }

    public List<DetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<DetailModel> details) {
        this.details = details;
    }
    
    
}
