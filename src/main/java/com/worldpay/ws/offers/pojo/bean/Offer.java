package com.worldpay.ws.offers.pojo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

	public Offer(long createdAt, String description, Double price, Long duration) {
		this.createdAt = createdAt;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, updatable = false, name = "OFFER_ID")
	private Long offerId;

    @NotNull
    @Size(min=1)
	@Column(unique = false, updatable = false, name = "DESCRIPTION")
    private String description;

    @NotNull
	@Column(unique = false, updatable = false, name = "PRICE")
    private Double price;

    @NotNull
	@Column(unique = false, updatable = false, name = "DURATION")
    private Long duration;
    
	@Column(unique = false, updatable = false, name = "CREATED_AT")
	private Long createdAt;
	
	@Column(unique = false, updatable = false, name = "EXPIRED")
	private Boolean expired;
}
