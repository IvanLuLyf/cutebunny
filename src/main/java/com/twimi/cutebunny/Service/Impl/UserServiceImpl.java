package com.twimi.cutebunny.Service.Impl;

import com.twimi.cutebunny.Dao.UserDao;
import com.twimi.cutebunny.Model.UserModel;
import com.twimi.cutebunny.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int register(String username, String email, String password) {
        return userDao.insert(new UserModel(username, email, password));
    }
}
