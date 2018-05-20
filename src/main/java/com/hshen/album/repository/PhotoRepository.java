package com.hshen.album.repository;

import com.hshen.album.model.Photo;
import com.hshen.album.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    Photo save(Photo photo);

    List<Photo> findByUser(User user);

    Photo findByPhotoId(Long photoId);

    List<Photo> findAll();

    List<Photo> findByUser_Id(ObjectId id);
}
