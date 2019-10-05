package com.worldpay.ws.offers.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.worldpay.ws.offers.api.service.OfferService;
import com.worldpay.ws.offers.pojo.bean.Offer;

@RestController
@RequestMapping("/worldpay/ws")
public class EntryController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/offer", method = RequestMethod.POST)
    public void addOffer(@Valid @RequestBody Offer offer, HttpServletResponse response) {
        offerService.addOffer(offer);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @RequestMapping(value = "/offerid/{offerId}", method = RequestMethod.GET)
    public Offer getOfferById(@PathVariable("offerId") long offerId) {
        return offerService.getOfferById(offerId);
    }

    @RequestMapping(value = "/offerid/{offerId}", method = RequestMethod.DELETE)
    public void deleteOfferById(@PathVariable("offerId") long offerId, HttpServletResponse response) {
        offerService.deleteOfferById(offerId);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    @RequestMapping(value = "/offer/{title}", method = RequestMethod.GET)
    public Offer getOfferByTitle(@PathVariable("title") String title) {
        return offerService.getOfferByTitle(title);
    }

    @RequestMapping(value = "/offer/{title}", method = RequestMethod.DELETE)
    public void deleteOfferById(@PathVariable("title") String title, HttpServletResponse response) {
        offerService.deleteOfferByTitle(title);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }
    
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }
}
