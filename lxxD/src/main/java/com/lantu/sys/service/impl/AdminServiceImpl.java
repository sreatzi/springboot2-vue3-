package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.sys.entity.Admin;
import com.lantu.sys.mapper.AdminMapper;
import com.lantu.sys.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private RedisTemplate redisTemplate;

    public AdminServiceImpl() {
    }

    public Map<String, Object> admin(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", admin.getUser()).eq("password", admin.getPassword());
        Admin userLogin = this.baseMapper.selectOne(queryWrapper);

        if (userLogin != null) {
            String key = "login:" + UUID.randomUUID();
            userLogin.setPassword(null);
            this.redisTemplate.opsForValue().set(key, userLogin, 30L, TimeUnit.MINUTES);
            Map<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        } else {
            return null;
        }
    }
}
