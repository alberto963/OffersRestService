package com.worldpay.ws.offers.api.repository.impl;

import static org.junit.Assert.assertEquals;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.worldpay.ws.offers.api.repository.OfferRepository;
import com.worldpay.ws.offers.pojo.bean.Offer;

/* REPOSITORY INTEGRATION TESTING with embedded in-memory database accessed through Spring JPA TestEntityManager */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OfferRepository offerRepository;

    @Test
    public void save_whenOfferIsSaved_thenCanBeRetrieved() {
        // given
        Offer validOffer = new Offer(1L, "description", 0L, 1L, null);
        testEntityManager.persist(validOffer);
        testEntityManager.flush();

        // when
        Offer found = offerRepository.findOne(validOffer.getOfferId());

        // then
        assertEquals(validOffer.getOfferId(), found.getOfferId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void validation_whenOfferHasEmptyDescription_thenConstraintViolationException() {
        // given
        Offer invalidOffer = new Offer(1L, "", 0L, 1L, null);

        // when
        testEntityManager.persist(invalidOffer);
        testEntityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void validation_whenOfferHasMissingDescription_thenConstraintViolationException() {
        // given
        Offer invalidOffer = new Offer(1L, null, 0L, 1L, null);

        // when
        testEntityManager.persist(invalidOffer);
        testEntityManager.flush();
    }
}
