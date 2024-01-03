package com.lantu.sys.service;

import com.lantu.sys.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface IRoomService extends IService<Room> {
    boolean saveRoom(String roomid,String room,String msize,String roomstat,String airconditioning,String wifi);
    boolean updateRoom(String roomid, String valueOf, String valueOf1, String valueOf2, String valueOf3, String valueOf4);

    boolean delete(String roomid);
}
