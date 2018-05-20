package com.hshen.album.service.impl;

import com.hshen.album.model.User;
import com.hshen.album.repository.UserRepository;
import com.hshen.album.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    };

    @Override
    public User findByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByName(userName);
    }
}
