package com.hshen.album.service.impl;

import com.hshen.album.model.Comment;
import com.hshen.album.repository.CommentRepository;
import com.hshen.album.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> findByPhotoId(String photoId, Pageable pageable) {
        return commentRepository.findByPhotoId(photoId, pageable);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }


}
