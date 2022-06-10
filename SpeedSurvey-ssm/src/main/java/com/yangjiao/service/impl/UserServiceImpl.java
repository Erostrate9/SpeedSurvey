package com.yangjiao.service.impl;

import com.yangjiao.domain.User;
import com.yangjiao.mapper.UserMapper;
import com.yangjiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> queryAllByCondition(User user) {
        return userMapper.selectAllByCondition(user);
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            return null;
        }
        userMapper.update(user);
        return userMapper.selectById(user.getId());
    }

    @Override
    public User deleteById(int id) {
        User user;
        user = userMapper.selectById(id);
        if (user == null) {
            return null;
        }
        userMapper.deleteById(id);
        return user;
    }

    @Override
    public List<User> deleteByIds(int[] ids) {
        ArrayList<User> users = new ArrayList<>();
        for (int id : ids) {
            users.add(userMapper.selectById(id));
        }
        if (users.size() == 0) {
            return null;
        }
        userMapper.deleteByIds(ids);
        return users;
    }

    @Override
    public User login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public User register(User user) {
        User publisherAlreadyExist = userMapper.selectByUsername(user.getUsername());
        if (publisherAlreadyExist != null) {
            return null;
        }
        userMapper.insert(user);
        return userMapper.selectById(user.getId());
    }

}
