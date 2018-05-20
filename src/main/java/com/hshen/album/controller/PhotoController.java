package com.hshen.album.controller;

import com.hshen.album.model.Photo;
import com.hshen.album.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/allPhotos")
    public List<Photo> getAllPhotos() {
        return photoService.findAll();
    }
}
