package com.hshen.album.service;

import com.hshen.album.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    Page<Comment> findByPhotoId(String photoId, Pageable pageable);

    Page<Comment> findAll(Pageable pageable);
}
