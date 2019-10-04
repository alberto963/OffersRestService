package com.worldpay.ws.offers.api.service;

import java.util.List;

import com.worldpay.ws.offers.api.error.exception.DuplicateOfferIdException;
import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.pojo.dto.BaseOfferDTO;
import com.worldpay.ws.offers.pojo.dto.OfferDTO;

public interface OfferService {
    public void addOffer(BaseOfferDTO offerDTO) throws DuplicateOfferIdException;
    public OfferDTO getOfferById(long offerId) throws ResourceNotFoundException;
	public void deleteOfferById(long offerId);
	public List<OfferDTO> getAllOffers();
}
