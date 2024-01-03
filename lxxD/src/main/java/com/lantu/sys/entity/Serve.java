package com.lantu.sys.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public class Serve implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serveid;

    private String servetype;

    private String serveall;

    private LocalDate servedata;

    private String userid;

    public String getServeid() {
        return serveid;
    }

    public void setServeid(String serveid) {
        this.serveid = serveid;
    }
    public String getServetype() {
        return servetype;
    }

    public void setServetype(String servetype) {
        this.servetype = servetype;
    }
    public String getServeall() {
        return serveall;
    }

    public void setServeall(String serveall) {
        this.serveall = serveall;
    }
    public LocalDate getServedata() {
        return servedata;
    }

    public void setServedata(LocalDate servedata) {
        this.servedata = servedata;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Serve{" +
            "serveid=" + serveid +
            ", servetype=" + servetype +
            ", serveall=" + serveall +
            ", servedata=" + servedata +
            ", userid=" + userid +
        "}";
    }
}
