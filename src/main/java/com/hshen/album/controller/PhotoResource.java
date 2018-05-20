package com.hshen.album.controller;

import com.hshen.album.model.Photo;
import com.hshen.album.model.User;
import com.hshen.album.service.PhotoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rest")
public class PhotoResource {
    private String imageName;

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "photo/upload", method = RequestMethod.POST)
    public String upload(HttpServletResponse response, HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartHttpServletRequest.getFileNames();
        MultipartFile multipartFile = multipartHttpServletRequest.getFile(it.next());

        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        String fileName = str + rannum + multipartFile.getOriginalFilename();
        imageName = fileName;
        String filePath = "D://images//";
        String path = filePath + fileName;

        try {
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Upload Image Success!";
    }

    @RequestMapping(value = "/photo/add", method = RequestMethod.POST)
    public Photo addPhoto(@RequestBody Photo photo) {
        photo.setImageName(imageName);
        photo.setCreated(new Date());
        return photoService.save(photo);
    }

    @RequestMapping(value = "/photo/user", method = RequestMethod.POST)
    public List getPhotoByUser(@RequestBody User user) {
        return photoService.findByUser(user);
    }

    @RequestMapping(value = "/photo/photoId", method = RequestMethod.POST)
    public Photo getPhotoByPhotoId(@RequestBody String photoId) {
        return photoService.findByPhotoId(photoId);
    }

    @RequestMapping(value = "/photo/{userId}", method = RequestMethod.GET)
    public List<Photo> getPhotoByUserId(@PathVariable ObjectId userId) {
        List<Photo> photoList = photoService.findByUserId(userId);
        return photoList;
    }
}
