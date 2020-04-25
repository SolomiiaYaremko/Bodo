package com.solobodo.dao;

import com.solobodo.entity.EntertainmentType;
import com.solobodo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferDao extends JpaRepository<Offer, Long> {

    Offer getOfferByTitle (String title);

    List<Offer> findAllByEntertainmentTypeId(Long entertainmentTypeId);
}
