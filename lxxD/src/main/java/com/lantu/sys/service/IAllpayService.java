package com.lantu.sys.service;

import com.lantu.sys.entity.Allpay;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface IAllpayService extends IService<Allpay> {
    boolean saveallpay(String allid,String allmoney);
    boolean delete(String allid);
}
