package com.lantu.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sreat
 * @since 2023-12-27
 */
public class Commit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String goodcommit;

    private String badcommit;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getGoodcommit() {
        return goodcommit;
    }

    public void setGoodcommit(String goodcommit) {
        this.goodcommit = goodcommit;
    }
    public String getBadcommit() {
        return badcommit;
    }

    public void setBadcommit(String badcommit) {
        this.badcommit = badcommit;
    }

    @Override
    public String toString() {
        return "Commit{" +
            "userid=" + userid +
            ", goodcommit=" + goodcommit +
            ", badcommit=" + badcommit +
        "}";
    }
}
