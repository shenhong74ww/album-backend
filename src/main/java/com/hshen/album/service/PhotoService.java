package com.hshen.album.service;

import com.hshen.album.model.Photo;
import com.hshen.album.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface PhotoService {
    Photo save(Photo photo);

    List<Photo> findByUser(User user);

    Photo findByPhotoId(String photoId);

    List<Photo> findAll();

    List<Photo> findByUserId(ObjectId userId);
}
