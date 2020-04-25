package com.solobodo.service;

import com.solobodo.dto.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    void save (CommentDto commentDto);
}
