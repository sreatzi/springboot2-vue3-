package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.sys.entity.Serve;
import com.lantu.sys.mapper.ServeMapper;
import com.lantu.sys.service.IServeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve> implements IServeService {
      @Autowired
      private RedisTemplate<String,Serve> redisTemplate;
      public ServeServiceImpl(){}

      public boolean saveServe(String serveid, String servetype, String serveall, String servedata, String userid){
          Serve serve = new Serve();
          serve.setServeid(serveid);
          serve.setServetype(servetype);
          serve.setServeall(serveall);
          serve.setServedata(LocalDate.parse(servedata));
          serve.setUserid(userid);
          String key = "serve:" + UUID.randomUUID().toString();
          this.redisTemplate.opsForValue().set(key,serve,30L, TimeUnit.MINUTES);
          return true;
      }


    public boolean delete(String serveid) {
        QueryWrapper<Serve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("serveid",serveid);
        ((ServeMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "serve:*";
        Set<String>keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();

        while(var5.hasNext()){
            String key = (String) var5.next();
            Serve serve = (Serve) this.redisTemplate.opsForValue().get(key);
            if (serve !=null && serve.getServeid().equals(serveid)){
                this.redisTemplate.delete(key);
            }
        }
        return true;
    }


}
