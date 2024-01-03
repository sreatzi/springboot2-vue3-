package com.lantu.sys.service;

import com.lantu.sys.entity.Recording;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface IRecordingService extends IService<Recording> {
    boolean saveRecording(String payid, String money, Date peydata, String userid);

    boolean delete(String userid);
}
