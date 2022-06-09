package com.yangjiao.service;

import com.yangjiao.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> queryAll();
    List<User> queryAllByCondition(User user);
    User update(User user);
    User deleteById(int id);
    List<User> deleteByIds(int[] ids);
    User login(String username, String password);
    User register(User user);
}
