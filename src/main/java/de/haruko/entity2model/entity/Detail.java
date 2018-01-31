/**
 * $HeadURL$
 * $LastChangedRevision$
 * $LastChangedDate$
 * $Author$
 * Created on 30.01.2018, 20:06:09
 * ===========================================================================
 * Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
 */
package de.haruko.entity2model.entity;

import java.util.Date;

public class Detail {

    private String id;
    private Referenz referenz;
    private String admin;
    private Date adminDate;

    public Detail() {
    }

    public Detail(String id, Referenz referenz, String admin, Date adminDate) {
        this.id = id;
        this.referenz = referenz;
        this.admin = admin;
        this.adminDate = adminDate;
    }

    public String getId() {
        return id;
    }

    public Detail setId(String id) {
        this.id = id;
        return this;
    }

    public Referenz getReferenz() {
        return referenz;
    }

    public Detail setReferenz(Referenz referenz) {
        this.referenz = referenz;
        return this;
    }

    public String getAdmin() {
        return admin;
    }

    public Detail setAdmin(String admin) {
        this.admin = admin;
        return this;
    }

    public Date getAdminDate() {
        return adminDate;
    }

    public Detail setAdminDate(Date adminDate) {
        this.adminDate = adminDate;
        return this;
    }
}
