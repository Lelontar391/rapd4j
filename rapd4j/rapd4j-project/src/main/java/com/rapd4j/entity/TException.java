package com.rapd4j.entity;

import java.util.Date;

public class TException {
    private String eid;

    private String eResource;

    private Date eCreated;

    private byte[] eMsg;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public String geteResource() {
        return eResource;
    }

    public void seteResource(String eResource) {
        this.eResource = eResource == null ? null : eResource.trim();
    }

    public Date geteCreated() {
        return eCreated;
    }

    public void seteCreated(Date eCreated) {
        this.eCreated = eCreated;
    }

    public byte[] geteMsg() {
        return eMsg;
    }

    public void seteMsg(byte[] eMsg) {
        this.eMsg = eMsg;
    }
}