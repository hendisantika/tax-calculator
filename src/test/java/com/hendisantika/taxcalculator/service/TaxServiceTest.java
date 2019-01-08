package com.hendisantika.taxcalculator.service;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.repository.TaxRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-09
 * Time: 06:14
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TaxServiceTest {
    @Autowired
    private TaxService taxService;

    @MockBean
    private RestTemplate template;

    @MockBean
    private TaxRepository taxRepository;

    @Before
    public void setUp() {
        Tax tax = new Tax();
        tax.setTaxId(2L);
        tax.setUserId("7be33735-9215-4d49-b669-960b9401e9ee");
        tax.setName("Movie");
        tax.setTaxCode(3);
        tax.setPrice(120.0);
        tax.setCreated(new Date());

        Mockito.when(taxRepository.findByTaxCode(tax.getTaxCode()))
                .thenReturn(tax);
    }

    @Test
    public void whenValidTaxCode_thenTaxShouldBeFound() {
        String name = "Movie";
        Integer taxCode = 3;
        Tax found = taxService.findByTaxCode(taxCode);

        assertThat(found.getTaxCode())
                .isEqualTo(taxCode);
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public TaxService taxService() {
            return new TaxService();
        }
    }
}