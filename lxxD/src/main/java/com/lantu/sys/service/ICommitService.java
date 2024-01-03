package com.lantu.sys.service;

import com.lantu.sys.entity.Commit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-27
 */
public interface ICommitService extends IService<Commit> {

    boolean updatecommit(String userid, String valueOf, String valueOf1);

    boolean delete(String userid);
    boolean savecommit(String userid,String goodcommit,String badcommit);
}
