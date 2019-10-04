package com.worldpay.ws.offers.pojo.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseOfferDTO {
// DTO: Data Transfer Object
	@NotNull
    private Long offerId;

    @NotNull
    @Size(min=1)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Long duration;
}
