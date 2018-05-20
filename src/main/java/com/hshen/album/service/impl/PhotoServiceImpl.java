package com.hshen.album.service.impl;

import com.hshen.album.model.Photo;
import com.hshen.album.model.User;
import com.hshen.album.repository.PhotoRepository;
import com.hshen.album.service.PhotoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public List<Photo> findByUser(User user) {
        return photoRepository.findByUser(user);
    }

    @Override
    public Photo findByPhotoId(String photoId) {
        return photoRepository.findOne(photoId);
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public List<Photo> findByUserId(ObjectId userId) {
        return photoRepository.findByUser_Id(userId);
    }
}
