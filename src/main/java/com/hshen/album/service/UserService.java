package com.hshen.album.service;

import com.hshen.album.model.User;

public interface UserService {
    User save(User user);

    User findByEmail(String email);

    User findByUserId(String userId);

    User findByUserName(String userName);
}
