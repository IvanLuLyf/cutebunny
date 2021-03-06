package cn.ivanlu.cutebunny.Service.Impl;

import cn.ivanlu.cutebunny.Dao.UserDao;
import cn.ivanlu.cutebunny.Model.UserModel;
import cn.ivanlu.cutebunny.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int register(String username, String email, String password) {
        return userDao.insert(new UserModel(username, email, password));
    }
}
