package com.lantu.sys.service;

import com.lantu.sys.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface IAdminService extends IService<Admin> {

    Map<String, Object> admin(Admin admin);
}
