package com.worldpay.ws.offers.controller.rest;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldpay.ws.offers.api.service.OfferService;
import com.worldpay.ws.offers.pojo.bean.Offer;

/* CONTROLLER INTEGRATION TESTING using MockMvc instance to setup a Spring MVC context with a web server */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EntryController.class, secure = false)
public class EntryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private OfferService offerService;

	private Offer dummyOffer, dummyOffer2;

	private static final String BASE_URL = "/worldpay/ws";
	private static final String OFFER_SUBPATH = "offer";
	private static final String OFFERS_SUBPATH = "offers";

	@Test
	public void get_whenOfferExists_thenResponseIs200() throws Exception {
		// given
		givenDummyOffer();
		given(offerService.getOfferById(dummyOffer.getOfferId())).willReturn(dummyOffer);

		// when-then
		mockMvc.perform(
				get(buildGetUrlWithIdVariable(OFFER_SUBPATH), dummyOffer.getOfferId()).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void delete_whenOfferExists_thenResponseIs200() throws Exception {
		// given
		givenDummyOffer();
		given(offerService.getOfferById(dummyOffer.getOfferId())).willReturn(dummyOffer);

		// when-then
		mockMvc.perform(
				delete(buildDeleteUrlWithIdVariable(OFFER_SUBPATH), dummyOffer.getOfferId()).contentType(APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void get_whenOfferExists_thenJsonResponseIsCorrect() throws Exception {
		// given
		givenDummyOffer();
		given(offerService.getOfferById(dummyOffer.getOfferId())).willReturn(dummyOffer);

		// when-then
		mockMvc.perform(
				get(buildGetUrlWithIdVariable(OFFER_SUBPATH), dummyOffer.getOfferId()).contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.offerId", is(dummyOffer.getOfferId().intValue())))
				.andExpect(jsonPath("$.description", is(dummyOffer.getDescription())));
	}

	@Test
	public void get_whenOffersExists_thenJsonResponseIsCorrect() throws Exception {
		// given
		givenDummyOffer();
		List<Offer> offers = new LinkedList<Offer>();
		offers.add(dummyOffer);
		givenDummyOffer2();
		offers.add(dummyOffer2);
		given(offerService.getAllOffers()).willReturn(offers);

		// when-then
		mockMvc.perform(
				get(buildGetUrl(OFFERS_SUBPATH)).contentType(APPLICATION_JSON))
				.andExpect(jsonPath("[1].offerId", is(dummyOffer2.getOfferId().intValue())))
				.andExpect(jsonPath("[0].offerId", is(dummyOffer.getOfferId().intValue())));
	}
	
	@Test
	public void post_whenOfferIsValid_thenResponseIs201() throws Exception {
		// given
		givenDummyOffer();
		doNothing().when(offerService).addOffer(dummyOffer);

		// when-then
		mockMvc.perform(post(buildPostUrl(OFFER_SUBPATH)).contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(dummyOffer))).andExpect(status().isCreated());
	}

	@Test
	public void post_whenOfferHasMissingId_thenResponseIs400() throws Exception {
		// given
		Offer invalidOffer = new Offer(null, "title", "description", 9.99D, 1L, 0L, false);
		doNothing().when(offerService).addOffer(invalidOffer);

		// when-then
		mockMvc.perform(post(buildPostUrl(OFFER_SUBPATH)).contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(invalidOffer))).andExpect(status().isBadRequest());
	}

	private void givenDummyOffer() {
		dummyOffer = new Offer(1L, "title", "description", 9.99D, 1L, 0L, false);
	}

	private void givenDummyOffer2() {
		dummyOffer2 = new Offer(2L, "title", "description", 9.99D, 1L, 0L, false);
	}
	
	private String buildPostUrl(String subPath) {
		return new StringBuilder().append(BASE_URL).append("/").append(subPath).toString();
	}

	private String buildGetUrl(String subPath) {
		return new StringBuilder().append(BASE_URL).append("/").append(subPath).toString();
	}

	private String buildGetUrlWithIdVariable(String subPath) {
		return new StringBuilder().append(BASE_URL).append("/").append(subPath).append("/{id}").toString();
	}
	
	private String buildDeleteUrlWithIdVariable(String subPath) {
		return new StringBuilder().append(BASE_URL).append("/").append(subPath).append("/{id}").toString();
	}
}