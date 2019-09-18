package com.worldpay.ws.offers.controller.rest;

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
import com.worldpay.ws.offers.pojo.dto.OfferDTO;

@RestController
@RequestMapping("/worldpay/ws")
public class EntryController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/offer", method = RequestMethod.POST)
    public void addOffer(@Valid @RequestBody OfferDTO offerDTO, HttpServletResponse response) {
        offerService.addOffer(offerDTO);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @RequestMapping(value = "/offer/{offerId}", method = RequestMethod.GET)
    public OfferDTO getOfferById(@PathVariable("offerId") long offerId) {
        return offerService.getOfferById(offerId);
    }

/*    // uncomment for additional dev-testing
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }*/
}
