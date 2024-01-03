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
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid1;

    private String roomid1;

    private String booktime;

    public String getUserid1() {
        return userid1;
    }

    public void setUserid1(String userid1) {
        this.userid1 = userid1;
    }
    public String getRoomid1() {
        return roomid1;
    }

    public void setRoomid1(String roomid1) {
        this.roomid1 = roomid1;
    }
    public String getBooktime() {
        return booktime;
    }

    public void setBooktime(String booktime) {
        this.booktime = booktime;
    }

    @Override
    public String toString() {
        return "Book{" +
            "userid1=" + userid1 +
            ", roomid1=" + roomid1 +
            ", booktime=" + booktime +
        "}";
    }
}
