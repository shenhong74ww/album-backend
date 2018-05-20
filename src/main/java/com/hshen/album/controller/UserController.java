package com.hshen.album.controller;

import com.hshen.album.model.Heat;
import com.hshen.album.model.Like;
import com.hshen.album.model.Photo;
import com.hshen.album.model.User;
import com.hshen.album.service.HeatService;
import com.hshen.album.service.PhotoService;
import com.hshen.album.service.UserService;
import com.hshen.album.utils.JsonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private HeatService heatService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody Map<String, String> json) throws ServletException{

        if(json.get("email") == null || json.get("password") == null) {
            throw new ServletException("Please fill in username and password");
        }

        String email = json.get("email");
        String password = json.get("password");

        User user = userService.findByEmail(email);
        if(user == null) {
            throw new ServletException("Email not found.");
        }

        String pwd = user.getPassword();

        if(!password.equals(pwd)) {
            throw new ServletException("Invalid login, please check your name and password.");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        map.put("name", user.getName());
        map.put("id", user.getId());

        return map;
    }

    @RequestMapping(value="/like", method = RequestMethod.POST)
    public String addLikes(@RequestBody User user) {
        String photoId = user.getLikeList().get(user.getLikeList().size() - 1).getPhotoId();
        Photo photo = photoService.findByPhotoId(photoId);

        User savedUser = userService.findByUserId(user.getId());
        user.setPassword(savedUser.getPassword());

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(today);
        Heat heat = heatService.findByPhotoAndDate(photo, date);

        photo.setLikes(photo.getLikes() + 1);
        photo.setTotal(photo.getTotal() + 1);
        photoService.save(photo);

        if (heat != null) {
            heat.setLikes(heat.getLikes() + 1);
            heat.setTotal(heat.getTotal() + 1);
            heat.setPhoto(photo);
            heatService.save(heat);
        } else {
            Heat newHeat = new Heat();
            newHeat.setViews(0);
            newHeat.setComments(0);
            newHeat.setLikes(1);
            newHeat.setTotal(1);
            newHeat.setDate(date);
            newHeat.setPhoto(photo);
            heatService.save(newHeat);
        }

        if (userService.save(user) != null) {
            return "success";
        }

        return "false";
    }

    @RequestMapping(value="/deleteLike", method = RequestMethod.POST)
    public String deleteLikes(@RequestBody HashMap<String, Object> deleteItem) {
        String photoId = (String)deleteItem.get("photoId");
        User user = JsonUtils.conveterObject(deleteItem.get("user"), User.class);

        Photo photo = photoService.findByPhotoId(photoId);

        User savedUser = userService.findByUserId(user.getId());
        user.setPassword(savedUser.getPassword());

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(today);
        Heat heat = heatService.findByPhotoAndDate(photo, date);

        photo.setLikes(photo.getLikes() - 1);
        photo.setTotal(photo.getTotal() - 1);
        photoService.save(photo);

        if (heat != null) {
            heat.setLikes(heat.getLikes() - 1);
            heat.setTotal(heat.getTotal() - 1);
            heat.setPhoto(photo);
            heatService.save(heat);
        }

        if (userService.save(user) != null) {
            return "success";
        }

        return "false";

    }
}
