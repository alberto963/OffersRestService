package com.worldpay.ws.offers.pojo.dto;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
public class OfferDTO extends BaseOfferDTO {

    public OfferDTO(Long offerId, String description, Double price, Long duration, Long createdAt, Boolean expired) {
		super(offerId, description, price, duration);
		this.createdAt = createdAt;
		this.expired = expired;
	}

    @NotNull
    private Long createdAt;
    
    @NotNull
    private Boolean expired;
}
