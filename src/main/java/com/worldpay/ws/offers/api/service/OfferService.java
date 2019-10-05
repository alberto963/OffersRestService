package com.worldpay.ws.offers.api.service;

import java.util.List;

import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.pojo.bean.Offer;

public interface OfferService {
	public void addOffer(Offer offer);
    public Offer getOfferByTitle(String title) throws ResourceNotFoundException;
    public Offer getOfferById(long offerId) throws ResourceNotFoundException;
	public void deleteOfferById(long offerId);
	public void deleteOfferByTitle(String title);
	public List<Offer> getAllOffers();
}
