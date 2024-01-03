package com.lantu.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String name;

    private String phone;

    private LocalDate roomdata;

    private String bookinfo;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDate getRoomdata() {
        return roomdata;
    }

    public void setRoomdata(LocalDate roomdata) {
        this.roomdata = roomdata;
    }
    public String getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(String bookinfo) {
        this.bookinfo = bookinfo;
    }

    @Override
    public String toString() {
        return "User{" +
            "userid=" + userid +
            ", name=" + name +
            ", phone=" + phone +
            ", roomdata=" + roomdata +
            ", bookinfo=" + bookinfo +
        "}";
    }

}
