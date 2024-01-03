package com.lantu.sys.service;

import com.lantu.sys.entity.User;
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
public interface IUserService extends IService<User> {
    boolean saveUser(String userid, String name, String phone, String bookinfo);
    Map<String, Object> user(User user);

    boolean updateUser(String userid, String bookinfo);

    boolean delete(String userid);

    boolean upalldateUser(String userid, String valueOf,  String valueOf2);

    boolean updateUser(String userid, String valueOf, String valueOf1, String valueOf2);
}
