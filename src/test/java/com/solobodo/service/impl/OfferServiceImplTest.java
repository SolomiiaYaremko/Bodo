package com.solobodo.service.impl;

import com.solobodo.entity.Offer;
import com.solobodo.entity.enum_.SortedType;
import com.solobodo.service.OfferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OfferServiceImplTest extends MainClass {

    @Autowired
    private OfferService offerService;

    @ParameterizedTest
    @CsvSource({"1, title1", "3, title3", "7, title7"})
    void testGetOfferByTitle_WithParam(Long id, String title) {
        Optional<Offer> optionalOffer = Optional.ofNullable(offerMapper.toEntity(createObject(id)));

        when(offerDao.getOfferByTitle(title)).thenReturn(optionalOffer.get());
        Offer offer = offerService.getOfferByTitle(title);

        assertAll(
                () -> assertEquals(title, offer.getTitle()),
                () -> assertEquals("some text", offer.getDescription())
        );
        verify(offerDao).getOfferByTitle(title);
    }

    @Nested
    @DisplayName("Checks_by_Title")
    class ByTitle {

        @Test
        void testGetOfferByTitle() {
            Optional<Offer> optionalOffer = Optional.ofNullable(offerMapper.toEntity(createObject(1L)));

            when(offerDao.getOfferByTitle(anyString())).thenReturn(optionalOffer.get());
            Offer offer = offerService.getOfferByTitle("title1");

            ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
            verify(offerDao).getOfferByTitle(captor.capture());
            String captorValue = captor.getValue();
            assertEquals(captorValue, offer.getTitle());
        }

        @Test
        void testExceptionGetOfferByTitle() {
            when(offerDao.getOfferByTitle(anyString())).thenReturn(null);
            ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> offerService.getOfferByTitle("title7"));
            assertEquals(exception.getMessage(), "404 NOT_FOUND " + '"' + "Not found Offer by title - title7" + '"', "Such Offer doesn't exist");
        }

        @Test
        void testExceptionGetOfferByTitle_Null() {
            when(offerDao.getOfferByTitle(anyString())).thenThrow(ResponseStatusException.class);
            assertThrows(ResponseStatusException.class, () -> offerService.getOfferByTitle(anyString()));
        }
    }

    @Test
    void testGetAll_contain() {
        when(offerDao.findAll()).thenReturn(getListOffer());
        List<Offer> offerList = offerService.getAll(Optional.of(SortedType.CHEAP));
        assertEquals(offerList.get(1), new Offer(3L, "title3", new BigDecimal(300), "some text"));
    }
}