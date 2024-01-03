package com.lantu.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lantu.sys.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("SELECT * FROM admin WHERE user = #{wrapper.entity.user} AND password = #{wrapper.entity.password}")
    Admin selectOne(@Param(Constants.WRAPPER) LambdaQueryWrapper<Admin> wrapper);
}
