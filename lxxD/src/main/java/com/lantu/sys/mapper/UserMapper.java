package com.lantu.sys.mapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lantu.sys.entity.Admin;
import com.lantu.sys.entity.User;
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
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT userid,name FROM user WHERE userid = #{wrapper.entity.userid} AND  = #{wrapper.entity.name}")
    User selectOne(@Param(Constants.WRAPPER) LambdaQueryWrapper<User> wrapper);
}
