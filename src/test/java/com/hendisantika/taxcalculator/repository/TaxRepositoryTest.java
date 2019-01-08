package com.hendisantika.taxcalculator.repository;

import com.hendisantika.taxcalculator.domain.Tax;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-08
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaxRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaxRepository taxRepository;

    @Test
    public void whenFindByTaxCodeThenReturnTaxItems() {
        // given
        Tax tax = new Tax();
        tax.setTaxId(1L);
        tax.setUserId("7be33735-9215-4d49-b669-960b9401e9ee");
        tax.setName("Marlboro");
        tax.setTaxCode(2);
        tax.setPrice(1000.0);
        tax.setCreated(new Date());

        entityManager.merge(tax);
        entityManager.flush();

        // when
        Tax found = taxRepository.findByTaxCode(tax.getTaxCode());

        // then
        assertThat(found.getTaxCode())
                .isEqualTo(tax.getTaxCode());
    }

    @Test
    public void saveTaxData() {
        // given
        Tax tax = new Tax();
        tax.setTaxId(2L);
        tax.setUserId("7be33735-9215-4d49-b669-960b9401e9ee");
        tax.setName("Movie");
        tax.setTaxCode(3);
        tax.setPrice(120.0);
        tax.setCreated(new Date());

        entityManager.merge(tax);
        entityManager.flush();

        // when
        Tax found = taxRepository.findByTaxCode(tax.getTaxCode());

        // then
        assertThat(found.getTaxCode())
                .isEqualTo(tax.getTaxCode());
        Assert.assertNotNull(tax.getTaxId());
        Assert.assertNotNull(tax.getName());
        Assert.assertEquals("Movie", tax.getName());
    }

//    @Test
//    public void testFindAll() {
//        Page<Tax> findAll(Pageable pageable);
//        List<Tax> expected = new ArrayList<>();
//        Page foundPage = new PageImpl<>(expected);
//
//        when(personRepositoryMock.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
//
//        List<Person> actual = repository.findPersonsForPage(SEARCH_TERM, PAGE_INDEX);
//
//        ArgumentCaptor<Pageable> pageSpecificationArgument = ArgumentCaptor.forClass(Pageable.class);
//        verify(personRepositoryMock, times(1)).findAll(any(Predicate.class), pageSpecificationArgument.capture());
//        verifyNoMoreInteractions(personRepositoryMock);
//
//        Pageable pageSpecification = pageSpecificationArgument.getValue();
//
//        assertEquals(PAGE_INDEX, pageSpecification.getPageNumber());
//        assertEquals(PaginatingPersonRepositoryImpl.NUMBER_OF_PERSONS_PER_PAGE, pageSpecification.getPageSize());
//        assertEquals(Sort.Direction.ASC, pageSpecification.getSort().getOrderFor(PROPERTY_LASTNAME).getDirection());
//
//        assertEquals(expected, actual);
//    }
}