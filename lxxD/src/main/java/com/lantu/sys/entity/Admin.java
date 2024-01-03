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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user;

    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
            "user=" + user +
            ", password=" + password +
        "}";
    }
}
