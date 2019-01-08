package com.hendisantika.taxcalculator.controller;

import com.hendisantika.taxcalculator.service.TaxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-09
 * Time: 06:40
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(TaxController.class)
public class TaxControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaxService taxService;


//    @Test
//    public void getTaxList() throws Exception{
//        Tax tax = new Tax();
//        tax.setTaxId(2L);
//        tax.setUserId("7be33735-9215-4d49-b669-960b9401e9ee");
//        tax.setName("Movie");
//        tax.setTaxCode(3);
//        tax.setPrice(120.0);
//        tax.setCreated(new Date());
//
//        Pageable pageable =
//
//        List<Tax> allTaxItem = Arrays.asList(tax);
//
//        given(taxService.getTaxList()).willReturn(allTaxItem);
//
//        mvc.perform(get("/api/employees")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(tax.getName())));
//    }

    @Test
    public void addTaxItemList() {
    }
}