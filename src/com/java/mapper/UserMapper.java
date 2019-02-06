package com.java.mapper;

import java.util.List;

import com.java.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAll();
    
    /**
     * 根据用户信息查用户详情（登录）
     * */
    User selectByUser(User user);
    /**
     * 根据用户名查用户详情
     * */
    User selectByName(String name);
    
    
}