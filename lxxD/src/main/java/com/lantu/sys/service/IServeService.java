package com.lantu.sys.service;

import com.lantu.sys.entity.Serve;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface IServeService extends IService<Serve> {
  boolean saveServe(String serveid, String servetype, String serveall, String servedata, String userid);

  boolean delete(String serveid);
}
