package com.worldpay.ws.offers.pojo.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BaseOfferDTO implements Serializable {

    private Long id;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
    @Size(min=3)
    private String offerId;

    @NotNull
    @Size(min=1)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Long duration;
    
    public BaseOfferDTO(Long id, String offerId, String description, Double price, Long duration) {
		super();
		this.id = id;
		this.offerId = offerId;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}

	public BaseOfferDTO() {
    }

	public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
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
