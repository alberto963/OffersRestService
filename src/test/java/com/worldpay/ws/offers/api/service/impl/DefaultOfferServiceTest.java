package com.worldpay.ws.offers.api.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.worldpay.ws.offers.api.error.exception.DuplicateOfferIdException;
import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.api.repository.OfferRepository;
import com.worldpay.ws.offers.api.service.impl.DefaultOfferService;
import com.worldpay.ws.offers.pojo.bean.Offer;
import com.worldpay.ws.offers.pojo.dto.OfferDTO;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOfferServiceTest {

    @InjectMocks
    private DefaultOfferService defaultOfferService;

    @Mock
    private OfferRepository offerRepository;

    private OfferDTO dummyOfferDTO;
    private Offer dummyOffer;

    @Test
    public void addOffer_whenOfferIsNotDuplicate_thenIsSaved() {
        // given
        givenDummyOfferDTO();
        given(offerRepository.findOne(dummyOfferDTO.getOfferId())).willReturn(null);

        // when
        defaultOfferService.addOffer(dummyOfferDTO);

        // then
        verify(offerRepository, times(1)).save(any(Offer.class));
    }

    @Test(expected = DuplicateOfferIdException.class)
    public void addOffer_whenOfferIsDuplicate_thenDuplicateOfferIdException() {
        // given
        givenDummyOfferDTO();
        given(offerRepository.findOne(anyLong())).willReturn(new Offer());

        // when
        defaultOfferService.addOffer(dummyOfferDTO);
    }

    @Test
    public void getOfferById_whenOfferExists_thenReturnIsSuccessful() {
        // given
        givenDummyOffer();
        given(offerRepository.findOne(anyLong())).willReturn(dummyOffer);

        // when
        OfferDTO resultOfferDTO = defaultOfferService.getOfferById(anyLong());

        // then
        assertNotNull(resultOfferDTO);
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
        givenDummyOfferDTO(1L);
        given(offerRepository.findOne(dummyOfferDTO.getOfferId())).willReturn(null);
        
        // when
        defaultOfferService.addOffer(dummyOfferDTO);
        
        givenDummyOfferDTO(2L);
        given(offerRepository.findOne(dummyOfferDTO.getOfferId())).willReturn(null);
        
        // when
        defaultOfferService.addOffer(dummyOfferDTO);

        // then
        verify(offerRepository, times(2)).save(any(Offer.class));
    }
    
    private void givenDummyOfferDTO() {
        dummyOfferDTO = new OfferDTO(1L, "description", 9.99D, 1L, 0L, false);
    }

    private void givenDummyOfferDTO(Long offerId) {
        dummyOfferDTO = new OfferDTO(offerId, "description", 9.99D, 1L, 0L, false);
    }
    
    private void givenDummyOffer() {
        dummyOffer = new Offer(1L, "description", 9.99, 1L, 0L);
    }
}
