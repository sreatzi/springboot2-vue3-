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
public class Recording implements Serializable {

    private static final long serialVersionUID = 1L;

    private String payid;

    private String money;

    private LocalDate peydata;

    private String userid;

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
    public LocalDate getPeydata() {
        return peydata;
    }

    public void setPeydata(LocalDate peydata) {
        this.peydata = peydata;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Recording{" +
            "payid=" + payid +
            ", money=" + money +
            ", peydata=" + peydata +
            ", userid=" + userid +
        "}";
    }
}
