package com.se_project.be.service.serviceImpl;


import com.se_project.be.dao.UserDAO;
import com.se_project.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.se_project.be.entity.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }
    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }
}

