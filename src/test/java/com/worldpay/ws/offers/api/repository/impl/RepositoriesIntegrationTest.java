package com.worldpay.ws.offers.api.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        Offer validOffer = new Offer(999999L, "title", "description", 9.99D, 0L);
        testEntityManager.persist(validOffer);
        testEntityManager.flush();

        // when
        Offer found = offerRepository.findOne(3L);

        // then
        assertEquals(validOffer.getDescription(), found.getDescription());
    }

    @Test
    public void save_whenOfferIsSaved_thenCanBeDeleted() {
        // given
        Offer validOffer = new Offer(999999L, "title", "description", 9.99D, 0L);;
        testEntityManager.persist(validOffer);
        testEntityManager.flush();

        // when
        offerRepository.delete(4L);

        Offer notFount = offerRepository.findOne(4L);
        assertNull(notFount);
    }
    
    @Test
    public void save_whenOfferAreSaved_thenCanBeAllRetrieved() {
        // given
        Offer validOffer1 = new Offer(999999L, "title", "description", 9.99D, 1L);
        testEntityManager.persist(validOffer1);
        Offer validOffer2 = new Offer(999999L, "title", "description", 9.99D, 1L);
        testEntityManager.persist(validOffer2);
        testEntityManager.flush();

        // when
        long found = offerRepository.count();

        // then
        assertEquals(2, found);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void validation_whenOfferHasEmptyDescription_thenConstraintViolationException() {
        // given
        Offer invalidOffer = new Offer(999999L, "title", "", 9.99D, 1L);

        // when
        testEntityManager.persist(invalidOffer);
        testEntityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void validation_whenOfferHasMissingDescription_thenConstraintViolationException() {
        // given
        Offer invalidOffer = new Offer(999999L, "title", null, 9.99D, 1L);

        // when
        testEntityManager.persist(invalidOffer);
        testEntityManager.flush();
    }
}
