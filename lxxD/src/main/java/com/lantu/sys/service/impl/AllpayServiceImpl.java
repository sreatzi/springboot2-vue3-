package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.sys.entity.Allpay;
import com.lantu.sys.mapper.AllpayMapper;
import com.lantu.sys.service.IAllpayService;
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
public class AllpayServiceImpl extends ServiceImpl<AllpayMapper, Allpay> implements IAllpayService {
    @Autowired
    private RedisTemplate<String,Allpay>redisTemplate;
    public AllpayServiceImpl(){}

    public boolean saveallpay(String allid,String allmoney){
        Allpay allpay = new Allpay();
        allpay.setAllid(allid);
        allpay.setAllmoney(allmoney);
        String key = "allpay:"+ UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,allpay,30L, TimeUnit.MINUTES);
        return true;
    }

    public boolean delete(String allid) {
        QueryWrapper<Allpay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("allid",allid);
        ((AllpayMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "allpay:*";
        Set<String>keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();

        while (var5.hasNext()){
            String key = (String)var5.next();
            Allpay allpay = (Allpay)this.redisTemplate.opsForValue().get(key);
            if (allpay!=null && allpay.getAllid().equals(allid)){
                this.redisTemplate.delete(key);
            }
        }
        return false;
    }
}
