package com.worldpay.ws.offers.pojo.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfferDTO extends BaseOfferDTO {

    private Long createdAt;
    
    private Boolean expired;

    public OfferDTO(Long offerId, String description, Double price, Long duration, Long createdAt, Boolean expired) {
		super(offerId, description, price, duration);
		this.createdAt = createdAt;
		this.expired = expired;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public OfferDTO() {
    }

    public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
}
