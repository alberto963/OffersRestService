package com.worldpay.ws.offers.api.service.impl;

import static com.worldpay.ws.offers.api.error.message.ExceptionMessage.EXCEPTION_MESSAGE_OFFER_NOT_FOUND;
import static com.worldpay.ws.offers.api.error.message.ExceptionMessage.EXCEPTION_MESSAGE_DUPLICATE_TITLE;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldpay.ws.offers.api.error.exception.DuplicateTitleException;
import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.api.repository.OfferRepository;
import com.worldpay.ws.offers.api.service.OfferService;
import com.worldpay.ws.offers.pojo.bean.Offer;

@Service
public class DefaultOfferService implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Override
	public void addOffer(Offer o) throws DuplicateTitleException {
		Offer o2 = offerRepository.findByTitle(o.getTitle());

		if (o2 != null)
			throw new DuplicateTitleException(o.getTitle(), EXCEPTION_MESSAGE_DUPLICATE_TITLE);
		
		long createdAt = System.currentTimeMillis();

		Offer offer = new Offer(createdAt, o.getTitle(), o.getDescription(), o.getPrice(), o.getDuration());
		offerRepository.save(offer);
	}

	@Override
	public Offer getOfferById(long offerId) throws ResourceNotFoundException {
		Offer o = offerRepository.findOne(offerId);

		if (o == null)
			throw new ResourceNotFoundException(offerId, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		String title = o.getTitle();
		String description = o.getDescription();
		double price = o.getPrice();
		long duration = o.getDuration();
		long createdAt = o.getCreatedAt();
		Boolean expired = System.currentTimeMillis() - createdAt > duration;

		return new Offer(offerId, title, description, price, duration, createdAt, expired);
	}

	@Override
	public void deleteOfferById(long offerId) throws ResourceNotFoundException {
		Offer o = offerRepository.findOne(offerId);

		if (o == null)
			throw new ResourceNotFoundException(offerId, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		offerRepository.delete(offerId);
	}

	@Override
	public Offer getOfferByTitle(String title) throws ResourceNotFoundException {
		Offer o = offerRepository.findByTitle(title);

		if (o == null)
			throw new ResourceNotFoundException(title, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		long offerId = o.getOfferId();
		String description = o.getDescription();
		double price = o.getPrice();
		long duration = o.getDuration();
		long createdAt = o.getCreatedAt();
		Boolean expired = System.currentTimeMillis() - createdAt > duration;

		return new Offer(offerId, title, description, price, duration, createdAt, expired);	}

	@Override
	public void deleteOfferByTitle(String title) {
		Offer o = offerRepository.findByTitle(title);

		if (o == null)
			throw new ResourceNotFoundException(title, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		offerRepository.delete(o.getOfferId());		
	}
	
	@Override
	public List<Offer> getAllOffers() {
		List<Offer> offers = new LinkedList<Offer>();
		offerRepository.findAll().forEach((o) -> {
			long duration = o.getDuration();
			long createdAt = o.getCreatedAt();
			o.setExpired(System.currentTimeMillis() - createdAt > duration);
			offers.add(o);
		});

		return offers;
	}
}
