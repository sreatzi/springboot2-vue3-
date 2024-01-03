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
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roomid;

    private String room;

    private String msize;

    private String roomstat;

    private String airconditioning;

    private String wifi;

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public String getMsize() {
        return msize;
    }

    public void setMsize(String msize) {
        this.msize = msize;
    }
    public String getRoomstat() {
        return roomstat;
    }

    public void setRoomstat(String roomstat) {
        this.roomstat = roomstat;
    }
    public String getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(String airconditioning) {
        this.airconditioning = airconditioning;
    }
    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return "Room{" +
            "roomid=" + roomid +
            ", room=" + room +
            ", msize=" + msize +
            ", roomstat=" + roomstat +
            ", airconditioning=" + airconditioning +
            ", wifi=" + wifi +
        "}";
    }
}
