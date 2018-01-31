/**
 * $HeadURL$
 * $LastChangedRevision$
 * $LastChangedDate$
 * $Author$
 * Created on 30.01.2018, 20:05:06
 * ===========================================================================
 * Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
 */
package de.haruko.entity2model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Master {

    private String id;
    private Date datum;
    private BigDecimal zahl;
    private String admin;
    private Date adminDate;
    private List<Detail> details;

    public Master() {
    }

    public Master(String id, Date datum, BigDecimal zahl, String admin, Date adminDate, List<Detail> details) {
        this.id = id;
        this.datum = datum;
        this.zahl = zahl;
        this.admin = admin;
        this.adminDate = adminDate;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public Master setId(String id) {
        this.id = id;
        return this;
    }

    public Date getDatum() {
        return datum;
    }

    public Master setDatum(Date datum) {
        this.datum = datum;
        return this;
    }

    public BigDecimal getZahl() {
        return zahl;
    }

    public Master setZahl(BigDecimal zahl) {
        this.zahl = zahl;
        return this;
    }

    public String getAdmin() {
        return admin;
    }

    public Master setAdmin(String admin) {
        this.admin = admin;
        return this;
    }

    public Date getAdminDate() {
        return adminDate;
    }

    public Master setAdminDate(Date adminDate) {
        this.adminDate = adminDate;
        return this;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public Master setDetails(List<Detail> details) {
        this.details = details;
        return this;
    }
        
}
