package com.assessment.user.service.impl;

import com.assessment.user.business.UserBusiness;
import com.assessment.user.domain.User;
import com.assessment.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserBusiness userBusiness;

    @Override
    public User createUser(User user) {
        return userBusiness.createUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return userBusiness.getUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userBusiness.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userBusiness.deleteUser(id);
    }
}
