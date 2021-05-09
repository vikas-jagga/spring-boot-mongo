package com.assessment.user.service;

import com.assessment.user.domain.User;

public interface UserService {

    User createUser(User user);

    User getUser(Integer id);

    User updateUser(User user);

    void deleteUser(Integer id);
}
