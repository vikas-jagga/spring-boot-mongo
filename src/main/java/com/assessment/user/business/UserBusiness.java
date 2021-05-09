package com.assessment.user.business;

import com.assessment.user.domain.User;

public interface UserBusiness {

    User createUser(User user);

    User getUser(Integer id);

    User updateUser(User user);

    void deleteUser(Integer id);
}
