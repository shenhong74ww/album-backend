package com.hshen.album.controller;

import com.hshen.album.model.User;
import com.hshen.album.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserResource {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/users")
    public String loginSuccess() {
        return "Login Successful!";
    }

    @RequestMapping(value = "/user/userId", method = RequestMethod.POST)
    public User findByUserId(@RequestBody String userId) {
        User user = userService.findByUserId(userId);

        if (user != null) {
            user.setPassword(null);
        }

        return user;
    };

    @RequestMapping(value = "/user/userName", method = RequestMethod.POST)
    public User findByUserName(@RequestBody String userName) {
        return userService.findByUserName(userName);
    };
}
