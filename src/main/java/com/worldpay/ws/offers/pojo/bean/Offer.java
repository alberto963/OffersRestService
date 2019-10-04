package com.worldpay.ws.offers.pojo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer  {

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
}
