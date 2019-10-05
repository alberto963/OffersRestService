package com.worldpay.ws.offers.api.service;

import java.util.List;

import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.pojo.bean.Offer;

public interface OfferService {
    public Offer getOfferById(long offerId) throws ResourceNotFoundException;
	public void deleteOfferById(long offerId);
	public List<Offer> getAllOffers();
	void addOffer(Offer offer);
}
