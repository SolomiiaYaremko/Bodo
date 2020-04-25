package com.solobodo.service.impl;

import com.solobodo.dao.CommentDao;
import com.solobodo.dao.OfferDao;
import com.solobodo.dto.CommentDto;
import com.solobodo.entity.Comment;
import com.solobodo.entity.Offer;
import com.solobodo.mapper.CommentMapper;
import com.solobodo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void save(CommentDto commentDto) {
        Offer offer = offerDao.findById(commentDto.getOfferId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = new Comment();
        comment.setOwner(commentDto.getOwner());
        comment.setMessage(commentDto.getMessage());
        comment.setOffer(offer);
        commentDao.save(comment);
    }
}
