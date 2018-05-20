package com.hshen.album.service.impl;

import com.hshen.album.model.Heat;
import com.hshen.album.model.Photo;
import com.hshen.album.repository.HeatRepository;
import com.hshen.album.service.HeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeatServiceImpl implements HeatService {
    @Autowired
    private HeatRepository heatRepository;

    @Override
    public Heat save(Heat heat) {
        return heatRepository.save(heat);
    }

    @Override
    public Heat findByPhotoAndDate(Photo photo, String date) {
        return heatRepository.findByDateAndPhoto(date, photo);
    }

    @Override
    public Heat findByDate(String date) {
        return heatRepository.findByDate(date);
    }

    @Override
    public Page<Heat> getTopTenPhotos(String date, Pageable pageable) {
        return heatRepository.findByDate(date, pageable);
    }


}
