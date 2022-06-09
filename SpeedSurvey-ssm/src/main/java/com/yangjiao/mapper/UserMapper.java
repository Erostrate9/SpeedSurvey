package com.yangjiao.mapper;

import com.yangjiao.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    List<User> selectAll();
    User selectById(int id);
    User selectByUsername(String username);
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    List<User> selectAllByCondition(User publisher);
    int insert(User publisher);
    int update(User publisher);
    int deleteById(int id);
    int deleteByIds(@Param("ids") int[] ids);
}
