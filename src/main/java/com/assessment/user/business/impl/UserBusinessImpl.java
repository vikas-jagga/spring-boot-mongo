package com.assessment.user.business.impl;

import com.assessment.user.business.UserBusiness;
import com.assessment.user.domain.User;
import com.assessment.user.repository.UserRepository;
import com.assessment.user.service.impl.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.assessment.user.domain.User.DB_SEQUENCE;

@Component
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Override
    public User createUser(User user) {
        user.setId(sequenceGeneratorService.getSequenceNumber(DB_SEQUENCE));
        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
