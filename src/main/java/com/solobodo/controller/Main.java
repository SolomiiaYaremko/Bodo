package com.solobodo.controller;

import com.solobodo.dto.CommentDto;
import com.solobodo.dto.OfferDto;
import com.solobodo.entity.Offer;
import com.solobodo.entity.enum_.SortedType;
import com.solobodo.service.CommentService;
import com.solobodo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Main {

    @Autowired
    private OfferService offerService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/getAll")
    public List<Offer> getAll(@RequestParam Optional<SortedType> sortedType) {
        return offerService.getAll(sortedType);
    }

    @GetMapping("/byTitle")
    public Offer getByTitle(@RequestParam String title) {
       return offerService.getOfferByTitle(title);
    }

    @GetMapping("/type")
    public List<OfferDto> getOfferByTypes(@RequestParam Long id){
        return offerService.getOfferByType(id);
    }


    @PostMapping("/comment")
    public void createComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
    }
}
