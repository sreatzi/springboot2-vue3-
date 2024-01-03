package com.lantu.sys.service.impl;

import com.lantu.sys.entity.Chabook;
import com.lantu.sys.entity.Commit;
import com.lantu.sys.entity.Room;
import com.lantu.sys.mapper.ChabookMapper;
import com.lantu.sys.service.IChabookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sreat
 * @since 2024-01-03
 */
@Service
public class ChabookServiceImpl extends ServiceImpl<ChabookMapper, Chabook> implements IChabookService {
    @Autowired
    private RedisTemplate<String, Commit> redisTemplate;
    public ChabookServiceImpl(){}
    public boolean saveChabook(String userid,String roomid,String bookdate){
        Chabook chabook = new Chabook();
        chabook.setRoomid(roomid);
        chabook.setUserid(userid);
        chabook.setBookdate(bookdate);
        String key = "chabook:"+ UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,chabook,30L, TimeUnit.MINUTES);
        return true;
    }
}
