package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.sys.entity.Recording;
import com.lantu.sys.mapper.RecordingMapper;
import com.lantu.sys.service.IRecordingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
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
public class RecordingServiceImpl extends ServiceImpl<RecordingMapper, Recording> implements IRecordingService {
    @Autowired
    private RedisTemplate<String,Recording> redisTemplate;
    public RecordingServiceImpl(){}

    public boolean saveRecording(String payid, String money, String peydata,String userid){
        Recording recording = new Recording();
        recording.setPayid(payid);
        recording.setMoney(money);
        recording.setPeydata(LocalDate.parse(peydata));
        recording.setUserid(userid);
        String key = "recording:"+ UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,recording,30L, TimeUnit.MINUTES);
        return true;
    }

    public boolean delete(String userid){
        QueryWrapper<Recording> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        ((RecordingMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "recording:*";
        Set<String>keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();

        while (var5.hasNext()){
            String key =(String) var5.next();
            Recording recording = (Recording) this.redisTemplate.opsForValue().get(key);
            if (recording!=null && recording.getUserid().equals(userid)){
                this.redisTemplate.delete(key);
            };
        }
        return true;
    }

    @Override
    public boolean saveRecording(String payid, String money, Date peydata, String userid) {
        return false;
    }


}
