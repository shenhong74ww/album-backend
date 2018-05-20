package com.hshen.album.controller;

import com.hshen.album.model.Heat;
import com.hshen.album.model.Photo;
import com.hshen.album.service.HeatService;
import com.hshen.album.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/heat")
public class HeatController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private HeatService heatService;

    @RequestMapping(value="/updateViews/{photoId}", method=RequestMethod.PUT)
    public Heat updateViews(@PathVariable String photoId) {
        Photo photo = photoService.findByPhotoId(photoId);
        if (photo != null) {

            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(today);
            Heat heat = heatService.findByPhotoAndDate(photo, date);

            photo.setViews(photo.getViews() + 1);
            photo.setTotal(photo.getTotal() + 1);
            photoService.save(photo);

            if (heat != null) {
                heat.setViews(heat.getViews() + 1);
                heat.setTotal(heat.getTotal() + 1);
                heat.setPhoto(photo);
                heatService.save(heat);
                return heat;
            } else {
                Heat newHeat = new Heat();
                newHeat.setViews(1);
                newHeat.setComments(0);
                newHeat.setLikes(0);
                newHeat.setTotal(1);
                newHeat.setDate(date);
                newHeat.setPhoto(photo);
                heatService.save(newHeat);
                return newHeat;
            }
        }

        return null;
    }
}
