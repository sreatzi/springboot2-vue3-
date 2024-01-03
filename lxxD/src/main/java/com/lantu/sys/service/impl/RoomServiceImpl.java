package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lantu.sys.entity.Room;
import com.lantu.sys.mapper.RoomMapper;
import com.lantu.sys.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Autowired
    private RedisTemplate<String,Room> redisTemplate;
    private RoomServiceImpl roomService;
    public RoomServiceImpl(){}

    public boolean updateRoom(String roomid, String room, String msize, String roomstat, String airconditioning, String wifi) {
        UpdateWrapper<Room> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("roomid", roomid)
                .set("room", room)
                .set("msize", msize)
                .set("roomstat", roomstat)
                .set("airconditioning", airconditioning)
                .set("wifi", wifi);

        boolean success = super.update(updateWrapper);
        if (success) {
            Room updatedRoom = new Room();
            updatedRoom.setRoomid(roomid);
            updatedRoom.setRoom(room);
            updatedRoom.setMsize(msize);
            updatedRoom.setRoomstat(roomstat);
            updatedRoom.setAirconditioning(airconditioning);
            updatedRoom.setWifi(wifi);
            redisTemplate.opsForValue().set("room:" + roomid, updatedRoom);
        }
        return success;
    }

    public boolean delete(String roomid) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roomid",roomid);
        ((RoomMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "room:*";
        Set<String>keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();
        while (var5.hasNext()){
            String key = (String) var5.next();
            Room room = (Room) this.redisTemplate.opsForValue().get(key);
            if(room!=null&&room.getRoomid().equals(roomid)){
                this.redisTemplate.delete(key);
            }
        }

        return false;
    }

    public boolean saveRoom(String roomid,String room,String msize,String roomstat,String airconditioning,String wifi){
        Room room1 = new Room();
        room1.setRoomid(roomid);
        room1.setRoom(room);
        room1.setMsize(msize);
        room1.setRoomstat(roomstat);
        room1.setAirconditioning(airconditioning);
        room1.setWifi(wifi);
        String key = "room:"+ UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,room1,30L, TimeUnit.MINUTES);
        return true;
    }

}
