package com.lantu.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sreat
 * @since 2024-01-03
 */
public class Chabook extends Commit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String roomid;

    private String bookdate;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    public String getBookdate() {
        return bookdate;
    }

    public void setBookdate(String bookdate) {
        this.bookdate = bookdate;
    }

    @Override
    public String toString() {
        return "Chabook{" +
            "userid=" + userid +
            ", roomid=" + roomid +
            ", bookdate=" + bookdate +
        "}";
    }
}
