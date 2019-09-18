package com.worldpay.ws.offers.pojo.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfferDTO implements Serializable {

    @NotNull
    private Long offerId;

    @NotNull
    @Size(min=1)
    private String description;

    @NotNull
    private Long price;

    @NotNull
    private Long duration;

    public OfferDTO() {
    }

    public OfferDTO(Long offerId, String description, Long price, Long duration) {
        this.offerId = offerId;
        this.description = description;
        this.price = price;
        this.duration = duration;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

}
