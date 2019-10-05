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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

	public Offer(long createdAt, String title, String description, Double price, Long duration) {
		this.createdAt = createdAt;
		this.title = title;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, updatable = false)
	private Long offerId;

	@NotNull
    @Size(min=3)
	@Column(unique = true, updatable = false)
    private String title;

    @NotNull
    @Size(min=1)
	@Column(unique = false, updatable = false)
    private String description;

    @NotNull
	@Column(unique = false, updatable = false)
    private Double price;

    @NotNull
	@Column(unique = false, updatable = false)
    private Long duration;
    
	@Column(unique = false, updatable = false)
	private Long createdAt;
	
	@Column(unique = false, updatable = true)
	private Boolean expired = false;
}
