package com.solobodo.service.impl;

import com.solobodo.dao.OfferDao;
import com.solobodo.dto.OfferDto;
import com.solobodo.entity.Offer;
import com.solobodo.mapper.OfferMapper;
import com.solobodo.service.OfferService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringJUnitWebConfig
@SpringBootTest
@AutoConfigureMockMvc
public abstract class MainClass {

    @MockBean
    protected OfferDao offerDao;

    @Autowired
    protected OfferMapper offerMapper;

    public OfferDto createObject(Long id){
        OfferDto offerDto = new OfferDto();
        offerDto.setId(id);
        offerDto.setTitle("title" + id);
        offerDto.setDescription("some text");
        offerDto.setPrice(new BigDecimal(100));
        return offerDto;
    }

    public List<Offer> getListOffer(){
        return Arrays.asList(
                offerMapper.toEntity(createObject(1L)),
                offerMapper.toEntity(createObject(3L)),
                offerMapper.toEntity(createObject(4L)),
                offerMapper.toEntity(createObject(6L))
        );
    }
}
