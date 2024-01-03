package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lantu.sys.entity.Commit;
import com.lantu.sys.entity.Recording;
import com.lantu.sys.entity.Room;
import com.lantu.sys.mapper.CommitMapper;
import com.lantu.sys.mapper.RecordingMapper;
import com.lantu.sys.service.ICommitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
 * @since 2023-12-27
 */
@Service
public class CommitServiceImpl extends ServiceImpl<CommitMapper, Commit> implements ICommitService {
    @Autowired
    private RedisTemplate<String, Commit> redisTemplate;
    public CommitServiceImpl(){}

    public boolean updatecommit(String userid, String valueOf, String valueOf1) {
        UpdateWrapper<Commit> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userid)
                .set("badcommit", valueOf)
                .set("goodcommit", valueOf1);
        boolean success = super.update(updateWrapper);
        if (success) {
            Commit updatedcommit = new Commit();
            updatedcommit.setUserid(userid);
            updatedcommit.setBadcommit(valueOf);
            updatedcommit.setGoodcommit(valueOf1);

            redisTemplate.opsForValue().set("userid:" + userid, updatedcommit);
        }
        return success;
    }


    public boolean delete(String userid) {
        QueryWrapper<Commit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        ((CommitMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "recording:*";
        Set<String> keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();

        while (var5.hasNext()){
            String key =(String) var5.next();
            Commit commit = (Commit) this.redisTemplate.opsForValue().get(key);
            if (commit!=null && commit.getUserid().equals(userid)){
                this.redisTemplate.delete(key);
            };
        }
        return true;
    }

    public boolean savecommit(String userid,String goodcommit,String badcommit){
        Commit commit = new Commit();
        commit.setUserid(userid);
        commit.setGoodcommit(goodcommit);
        commit.setBadcommit(badcommit);

        String key = "room:"+ UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,commit,30L, TimeUnit.MINUTES);
        return true;
    }

}
