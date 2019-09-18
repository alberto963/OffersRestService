package com.worldpay.ws.offers.controller.rest;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldpay.ws.offers.api.service.OfferService;
import com.worldpay.ws.offers.controller.rest.EntryController;
import com.worldpay.ws.offers.pojo.dto.OfferDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

	private OfferDTO dummyOfferDTO;

	private static final String BASE_URL = "/worldpay/ws";
	private static final String OFFER_SUBPATH = "offer";

	@Test
	public void get_whenOfferExists_thenResponseIs200() throws Exception {
		// given
		givenDummyOfferDTO();
		given(offerService.getOfferById(dummyOfferDTO.getOfferId())).willReturn(dummyOfferDTO);

		// when-then
		mockMvc.perform(
				get(buildGetUrlWithIdVariable(OFFER_SUBPATH), dummyOfferDTO.getOfferId()).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void get_whenOfferExists_thenJsonResponseIsCorrect() throws Exception {
		// given
		givenDummyOfferDTO();
		given(offerService.getOfferById(dummyOfferDTO.getOfferId())).willReturn(dummyOfferDTO);

		// when-then
		mockMvc.perform(
				get(buildGetUrlWithIdVariable(OFFER_SUBPATH), dummyOfferDTO.getOfferId()).contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.offerId", is(dummyOfferDTO.getOfferId().intValue())))
				.andExpect(jsonPath("$.description", is(dummyOfferDTO.getDescription())));
	}

	@Test
	public void post_whenOfferIsValid_thenResponseIs201() throws Exception {
		// given
		givenDummyOfferDTO();
		doNothing().when(offerService).addOffer(dummyOfferDTO);

		// when-then
		mockMvc.perform(post(buildPostUrl(OFFER_SUBPATH)).contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(dummyOfferDTO))).andExpect(status().isCreated());
	}

	@Test
	public void post_whenOfferHasMissingId_thenResponseIs400() throws Exception {
		// given
		OfferDTO invalidOfferDTO = new OfferDTO(null, "description", 0L, 1L);
		doNothing().when(offerService).addOffer(invalidOfferDTO);

		// when-then
		mockMvc.perform(post(buildPostUrl(OFFER_SUBPATH)).contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(invalidOfferDTO))).andExpect(status().isBadRequest());
	}

	private void givenDummyOfferDTO() {
		dummyOfferDTO = new OfferDTO(1L, "description", 0L, 1L);
	}

	private String buildPostUrl(String subPath) {
		return new StringBuilder().append(BASE_URL).append("/").append(subPath).toString();
	}

	private String buildGetUrlWithIdVariable(String subPath) {
		return new StringBuilder().append(BASE_URL).append("/").append(subPath).append("/{id}").toString();
	}
}