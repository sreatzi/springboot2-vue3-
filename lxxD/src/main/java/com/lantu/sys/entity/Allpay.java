package com.lantu.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public class Allpay implements Serializable {

    private static final long serialVersionUID = 1L;

    private String allid;

    private String allmoney;

    public String getAllid() {
        return allid;
    }

    public void setAllid(String allid) {
        this.allid = allid;
    }
    public String getAllmoney() {
        return allmoney;
    }

    public void setAllmoney(String allmoney) {
        this.allmoney = allmoney;
    }

    @Override
    public String toString() {
        return "Allpay{" +
            "allid=" + allid +
            ", allmoney=" + allmoney +
        "}";
    }
}
