package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lantu.sys.entity.Room;
import com.lantu.sys.entity.Serve;
import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.ServeMapper;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    public UserServiceImpl() {
    }

    public boolean saveUser(String userid,String name,String phone,String roomdata,String bookinfo){
        User user = new User();
        user.setUserid(userid);
        user.setName(name);
        user.setPhone(phone);
        user.setRoomdata(LocalDate.parse(roomdata));
        user.setBookinfo(bookinfo);
        String key = "user:" + UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,user,30L,TimeUnit.MINUTES);
        return true;
    }


    @Override
    public boolean saveUser(String userid, String name, String phone, String bookinfo) {
        return false;
    }

    public Map<String, Object> user(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "phone") // 只选择 userid 和 name 列
                .eq("userid", user.getUserid())
                .eq("phone", user.getPhone());
        User userlogin = this.baseMapper.selectOne(queryWrapper);

        if (userlogin != null) {
            String key = "login" + UUID.randomUUID();
            userlogin.setName(null);
            this.redisTemplate.opsForValue().set(key, userlogin, 30L, TimeUnit.MINUTES);
            Map<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(String userid, String bookinfo) {
        UpdateWrapper<User>updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid",userid).set("bookinfo",bookinfo);

        boolean success = super.update(updateWrapper);
        if (success){
            User user = new User();
            user.setUserid(userid);
            user.setBookinfo(bookinfo);
            redisTemplate.opsForValue().set("user:"+userid,user);
        }

        return success;
    }


    public boolean delete(String userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        ((UserMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "serve:*";
        Set<String> keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();

        while(var5.hasNext()){
            String key = (String) var5.next();
            Serve serve = (Serve) this.redisTemplate.opsForValue().get(key);
            if (serve !=null && serve.getServeid().equals(userid)){
                this.redisTemplate.delete(key);
            }
        }
        return true;
    }

    @Override
    public boolean upalldateUser(String userid, String valueOf, String valueOf2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userid)
                .set("name", valueOf)
                .set("phone", valueOf2);

        boolean success = super.update(updateWrapper);
        if (success) {
            User updatedRoom = new User();
            updatedRoom.setUserid(userid);
            updatedRoom.setPhone(valueOf2);
            updatedRoom.setName(valueOf);
            redisTemplate.opsForValue().set("room:" + userid, updatedRoom);
        }
        return success;
    }

    @Override
    public boolean updateUser(String userid, String valueOf, String valueOf1, String valueOf2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userid)
                .set("name", valueOf)
                .set("phone", valueOf1)
                .set("bookinfo",valueOf2);
        boolean success = super.update(updateWrapper);
        if (success) {
            User updatedRoom = new User();
            updatedRoom.setUserid(userid);
            updatedRoom.setPhone(valueOf1);
            updatedRoom.setBookinfo(valueOf2);
            updatedRoom.setName(valueOf);
            redisTemplate.opsForValue().set("room:" + userid, updatedRoom);
        }
        return success;
    }


}