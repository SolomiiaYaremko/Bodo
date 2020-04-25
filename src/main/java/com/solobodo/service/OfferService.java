package com.solobodo.service;

import com.solobodo.dto.OfferDto;
import com.solobodo.entity.Offer;
import com.solobodo.entity.enum_.SortedType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OfferService {

    Offer getOfferByTitle (String title);

    List<Offer> getAll(Optional<SortedType> sortedTyp);

    List<OfferDto> getOfferByType(Long id);
}
