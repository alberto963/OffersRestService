package com.worldpay.ws.offers.api.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.worldpay.ws.offers.api.error.exception.DuplicateTitleException;
import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.api.repository.OfferRepository;
import com.worldpay.ws.offers.pojo.bean.Offer;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOfferServiceTest {

    @InjectMocks
    private DefaultOfferService defaultOfferService;

    @Mock
    private OfferRepository offerRepository;

    private Offer dummyOffer;

    @Test
    public void addOffer_whenOfferIsNotDuplicate_thenIsSaved() {
        // given
        givenDummyOffer();
        given(offerRepository.findOne(dummyOffer.getOfferId())).willReturn(null);

        // when
        defaultOfferService.addOffer(dummyOffer);

        // then
        verify(offerRepository, times(1)).save(any(Offer.class));
    }

    @Test(expected = DuplicateTitleException.class)
    public void addOffer_whenOfferIsDuplicate_thenDuplicateOfferIdException() {
        // given
        givenDummyOffer();
        given(offerRepository.findOne(anyLong())).willReturn(new Offer());

        // when
        defaultOfferService.addOffer(dummyOffer);
    }

    @Test
    public void getOfferById_whenOfferExists_thenReturnIsSuccessful() {
        // given
        givenDummyOffer();
        given(offerRepository.findOne(anyLong())).willReturn(dummyOffer);

        // when
        Offer resultOffer = defaultOfferService.getOfferById(anyLong());

        // then
        assertNotNull(resultOffer);
    }
    
    @Test
    public void deleteOfferById_whenOfferExists_thenReturnIsSuccessful() {
        // given
        givenDummyOffer();
        given(offerRepository.findOne(anyLong())).willReturn(new Offer());

        // when
        defaultOfferService.deleteOfferById(anyLong());

        // then
        verify(offerRepository, times(1)).delete(anyLong());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getOfferById_whenOfferNotExist_thenResourceNotFoundException() {
        // given
        given(offerRepository.findOne(anyLong())).willReturn(null);

        // when
        defaultOfferService.getOfferById(anyLong());
    }

    @Test
    public void addOffers_whenOfferAreNotDuplicate_thenAreSaved() {
        // given
        givenDummyOffer(1L);
        given(offerRepository.findOne(dummyOffer.getOfferId())).willReturn(null);
        
        // when
        defaultOfferService.addOffer(dummyOffer);
        
        givenDummyOffer(2L);
        given(offerRepository.findOne(dummyOffer.getOfferId())).willReturn(null);
        
        // when
        defaultOfferService.addOffer(dummyOffer);

        // then
        verify(offerRepository, times(2)).save(any(Offer.class));
    }
    
    private void givenDummyOffer() {
        dummyOffer = new Offer(1L, "title", "description", 9.99D, 1L, 0L, false);
    }

    private void givenDummyOffer(Long offerId) {
        dummyOffer = new Offer(offerId, "title", "description", 9.99D, 1L, 0L, false);
    }
}
