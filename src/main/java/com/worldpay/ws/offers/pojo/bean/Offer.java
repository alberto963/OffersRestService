package com.worldpay.ws.offers.pojo.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Offer implements Serializable {

	@Id
	@NotNull
	@Column(unique = true, updatable = false, name = "OFFER_ID")
	private Long offerId;

	@NotNull
	@Size(min = 1)
	private String description;

	@NotNull
	@Column(unique = false, updatable = false, name = "PRICE")
	private Double price;

	@NotNull
	@Column(unique = false, updatable = false, name = "DURATION")
	private Long duration;

	@NotNull
	@Column(unique = false, updatable = false, name = "CREATED_AT")
	private Long createdAt;

	public Offer() {
	}

	public Offer(Long offerId, String description, Double price, Long duration, Long createdAt) {
		this.offerId = offerId;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.createdAt = createdAt;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Offer))
			return false;

		Offer offer = (Offer) o;

		if (offerId != null ? !offerId.equals(offer.offerId) : offer.offerId != null)
			return false;
		if (description != null ? !description.equals(offer.description) : offer.description != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = offerId != null ? offerId.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
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
