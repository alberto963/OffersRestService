package com.worldpay.ws.offers.api.service;

import com.worldpay.ws.offers.api.error.exception.DuplicateOfferIdException;
import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.pojo.dto.BaseOfferDTO;
import com.worldpay.ws.offers.pojo.dto.OfferDTO;

public interface OfferService {

    void addOffer(BaseOfferDTO offerDTO) throws DuplicateOfferIdException;

    OfferDTO getOfferById(long offerId) throws ResourceNotFoundException;

	void deleteOfferById(long offerId);

/*    List<Offer> getAllOffers();*/
}
