package com.worldpay.ws.offers.pojo.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BaseOfferDTO implements Serializable {

	@NotNull
    private Long offerId;

    @NotNull
    @Size(min=1)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Long duration;
    
    public BaseOfferDTO(Long offerId, String description, Double price, Long duration) {
		super();
		this.offerId = offerId;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}

	public BaseOfferDTO() {
    }

	public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

}
