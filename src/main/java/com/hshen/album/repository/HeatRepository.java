package com.hshen.album.repository;

import com.hshen.album.model.Heat;
import com.hshen.album.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeatRepository extends MongoRepository<Heat, String> {
    Heat save(Heat heat);

    Heat findByDateAndPhoto(String date, Photo photo);

    Heat findByDate(String date);

    Page<Heat> findByDate(String date, Pageable pageable);
}
