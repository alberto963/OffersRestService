package com.worldpay.ws.offers.api.service.impl;

import static com.worldpay.ws.offers.api.error.message.ExceptionMessage.EXCEPTION_MESSAGE_OFFER_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.api.repository.OfferRepository;
import com.worldpay.ws.offers.api.service.OfferService;
import com.worldpay.ws.offers.pojo.bean.Offer;

@Service
public class DefaultOfferService implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	/*
	 * If several POST requests pop-in in parallel, some kind of concurrency-control
	 * is required to prevent phantom-reads when inserting a new offer. At this
	 * stage, we can enforce a full pessimistic locking at the JPA level with:
	 * 
	 * @Transactional(isolation = Isolation.SERIALIZABLE)
	 * 
	 * so that all transactions will be executed in a sequence with no overlapping.
	 * The effect is very similar to using synchronized.
	 * 
	 * Actually this locking is an overkill; it can cause performance issues and
	 * could be NOT a production solution.
	 * 
	 * In a real production environment we would possibly leverage the native
	 * locking of an ACID database.
	 */
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void addOffer(Offer baseOffer) {

		long createdAt = System.currentTimeMillis();

		Offer offer = new Offer(createdAt, baseOffer.getDescription(), baseOffer.getPrice(), baseOffer.getDuration());
		offerRepository.save(offer);
	}

	@Override
	public Offer getOfferById(long offerId) throws ResourceNotFoundException {
		Offer offer = offerRepository.findOne(offerId);

		if (offer == null)
			throw new ResourceNotFoundException(offerId, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		String description = offer.getDescription();
		double price = offer.getPrice();
		long duration = offer.getDuration();
		long createdAt = offer.getCreatedAt();
		Boolean expired = System.currentTimeMillis() - createdAt > duration;

		return new Offer(offerId, description, price, duration, createdAt, expired);
	}

	@Override
	public void deleteOfferById(long offerId) throws ResourceNotFoundException {
		Offer offer = offerRepository.findOne(offerId);

		if (offer == null)
			throw new ResourceNotFoundException(offerId, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		offerRepository.delete(offerId);
	}

	@Override
	public List<Offer> getAllOffers() {
		List<Offer> offers = (List<Offer>) offerRepository.findAll();

		return offers;
	}
}
