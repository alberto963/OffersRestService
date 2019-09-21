package com.worldpay.ws.offers.api.service.impl;

import static com.worldpay.ws.offers.api.error.message.ExceptionMessage.EXCEPTION_MESSAGE_DUPLICATE_OFFER_ID;
import static com.worldpay.ws.offers.api.error.message.ExceptionMessage.EXCEPTION_MESSAGE_OFFER_NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.worldpay.ws.offers.api.error.exception.DuplicateOfferIdException;
import com.worldpay.ws.offers.api.error.exception.ResourceNotFoundException;
import com.worldpay.ws.offers.api.repository.OfferRepository;
import com.worldpay.ws.offers.api.service.OfferService;
import com.worldpay.ws.offers.pojo.bean.Offer;
import com.worldpay.ws.offers.pojo.dto.OfferDTO;

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
	public void addOffer(OfferDTO offerDTO) throws DuplicateOfferIdException {

		/*
		 * CAVEAT In Spring JPA, SimpleJpaRepository.save() is implemented as follows:
		 * 
		 * @Transactional public <S extends T> S save(S entity) {
		 * if(this.entityInformation.isNew(entity)) { this.em.persist(entity); return
		 * entity; } else { return this.em.merge(entity); } }
		 * 
		 * this is trying to merge if the record already exists; thus if the ID already
		 * exists in the database, the insertion is actually treated as an update.
		 * 
		 * A workaround is to use an automatic generation for IDs (e.g.
		 * use @GeneratedValue on ID) which will prevent at any time to have two
		 * identical IDs. However, as for the @Entity Offer, it is more comfortable to
		 * choose the offerID manually for practical purposes when testing the
		 * application with a client.
		 * 
		 * At this point, to avoid the "merge" behavior, we have to make a check if the
		 * passed ID is already occupied by another offer, and reject the insertion in
		 * that case.
		 */

		Offer duplicateOffer = offerRepository.findOne(offerDTO.getOfferId());

		if (duplicateOffer != null)
			throw new DuplicateOfferIdException(offerDTO.getOfferId(), EXCEPTION_MESSAGE_DUPLICATE_OFFER_ID);

		long offerId = offerDTO.getOfferId();
		String description = offerDTO.getDescription();
		double price = offerDTO.getPrice();
		long duration = offerDTO.getDuration();

		Offer offer = new Offer(offerId, description, price, duration, null);
		offerRepository.save(offer);
	}

	@Override
	public OfferDTO getOfferById(long offerId) throws ResourceNotFoundException {
		Offer offer = offerRepository.findOne(offerId);

		if (offer == null)
			throw new ResourceNotFoundException(offerId, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		String description = offer.getDescription();
		double price = offer.getPrice();
		long duration = offer.getDuration();

		return new OfferDTO(offerId, description, price, duration);
	}

	@Override
	public void deleteOfferById(long offerId) throws ResourceNotFoundException {
		Offer offer = offerRepository.findOne(offerId);

		if (offer == null)
			throw new ResourceNotFoundException(offerId, EXCEPTION_MESSAGE_OFFER_NOT_FOUND);

		offerRepository.delete(offerId);		
	}

	/*
	 * @Override public List<Offer> getAllOffers() { List<Offer> offers = new
	 * LinkedList<>(); offerRepository.findAll() .forEach(offers::add); return
	 * offers; }
	 */
}
