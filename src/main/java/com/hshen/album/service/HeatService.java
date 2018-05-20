package com.hshen.album.service;

import com.hshen.album.model.Heat;
import com.hshen.album.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HeatService {
    Heat save(Heat heat);

    Heat findByPhotoAndDate(Photo photo, String date);

    Heat findByDate(String date);

    Page<Heat> getTopTenPhotos(String date, Pageable pageable);
}
