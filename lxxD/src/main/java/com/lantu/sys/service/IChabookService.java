package com.lantu.sys.service;

import com.lantu.sys.entity.Chabook;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2024-01-03
 */
public interface IChabookService extends IService<Chabook> {
    boolean saveChabook(String userid,String roomid,String bookdate);
}
