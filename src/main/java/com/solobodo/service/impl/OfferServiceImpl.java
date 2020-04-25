package com.solobodo.service.impl;

import com.solobodo.dao.OfferDao;
import com.solobodo.dao.TypeDao;
import com.solobodo.dto.OfferDto;
import com.solobodo.entity.EntertainmentType;
import com.solobodo.entity.Offer;
import com.solobodo.entity.enum_.SortedType;
import com.solobodo.mapper.OfferMapper;
import com.solobodo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private OfferMapper offerMapper;

    @Override
    public Offer getOfferByTitle(String title) {
        Optional<Offer> offer = Optional.ofNullable(offerDao.getOfferByTitle(title));
        return offer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Offer by title - " + title));
    }

    @Override
    public List<Offer> getAll(Optional<SortedType> sortedTyp) {
        if (sortedTyp.isPresent()) {
            SortedType type = sortedTyp.get();
            if (type.equals(SortedType.CHEAP)) {
                return offerDao.findAll()
                        .stream()
                        .limit(5)
                        .sorted(Comparator.comparing(Offer::getPrice))
                        .collect(Collectors.toList());
            } else if (type.equals(SortedType.EXPENSIVE)) {
                return offerDao.findAll()
                        .stream().limit(5)
                        .sorted(Comparator.comparing(Offer::getPrice)
                        .reversed())
                        .collect(Collectors.toList());
            }
        }
        return offerDao.findAll().stream().limit(5).sorted(Comparator.comparing(Offer::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> getOfferByType(Long id) {
        EntertainmentType entertainmentType = typeDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return offerDao.findAllByEntertainmentTypeId(entertainmentType.getId())
                .stream().map(offerMapper::toDto)
                .collect(Collectors.toList());
    }
}
