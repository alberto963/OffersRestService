package com.worldpay.ws.offers.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.worldpay.ws.offers.pojo.bean.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
	Offer findByTitle(String title);
}
