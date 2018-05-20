package com.hshen.album.controller;

import com.hshen.album.model.Comment;
import com.hshen.album.model.Heat;
import com.hshen.album.model.Photo;
import com.hshen.album.service.CommentService;
import com.hshen.album.service.HeatService;
import com.hshen.album.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    PhotoService photoService;

    @Autowired
    HeatService heatService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public Comment createComment(@RequestBody Comment comment) {
        if (!StringUtils.isEmpty(comment.getPhotoId())) {
            Photo photo = photoService.findByPhotoId(comment.getPhotoId());
            if (photo != null) {

                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(today);
                Heat heat = heatService.findByPhotoAndDate(photo, date);

                photo.setComments(photo.getComments() + 1);
                photo.setTotal(photo.getTotal() + 1);
                photoService.save(photo);

                heat.setComments(heat.getComments() + 1);
                heat.setTotal(heat.getTotal() + 1);
                heat.setPhoto(photo);
                heatService.save(heat);

                comment.setCreated_at(today);
                return commentService.save(comment);
            }

            return null;

        }

        return null;
    }

    @RequestMapping(value = "/comments/{photoId}", method=RequestMethod.GET)
    public Page<Comment> getCommentList(@PathVariable String photoId, HttpServletRequest request) {

        String pageSize = request.getParameter("pageSize");
        String pageIndex = request.getParameter("pageIndex");
        int page = Integer.parseInt(pageIndex);
        int size = Integer.parseInt(pageSize);
        Sort sort = new Sort(Sort.Direction.DESC, "created_at");
        Pageable pageable = new PageRequest(page, size, sort);
        return commentService.findByPhotoId(photoId, pageable);
    }
}
