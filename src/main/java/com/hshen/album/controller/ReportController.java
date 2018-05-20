package com.hshen.album.controller;

import com.hshen.album.model.Heat;
import com.hshen.album.model.Photo;
import com.hshen.album.service.HeatService;
import com.hshen.album.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    HeatService heatService;

    @RequestMapping(value = "/topTen", method = RequestMethod.GET)
    public List<Heat> getTopTenPhotos() {
        List<String> orders= new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC, "total");
        Pageable pageable= new PageRequest(0, 10, sort);

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(today);

        return heatService.getTopTenPhotos(date, pageable).getContent();
    }

}
