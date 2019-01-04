package com.hendisantika.taxcalculator.controller;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.dto.TaxDTO;
import com.hendisantika.taxcalculator.service.TaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-04
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("tax")
public class TaxController {
    private static Logger logger = LoggerFactory.getLogger(TaxController.class);

    @Autowired
    TaxService taxService;

    @GetMapping
    List<Tax> getTaxList() {
        logger.info("Data --> {}", taxService.getTaxList());
        return taxService.getTaxList();

    }

    @GetMapping("add")
    List<TaxDTO> addTaxItemList(@RequestBody TaxDTO taxDTO, @RequestParam(value = "requestId", required = false) final String requestId) {
        return taxService.addTaxItem(taxDTO, requestId);

    }

    @PostMapping
    Tax saveTax(@RequestBody TaxDTO taxDTO) {
        Set<TaxDTO> taxDTOSet = new HashSet<TaxDTO>();
//        for (Long productId:productIds){
//            taxDTOSet.add(productRepository.findOne(productId));
//        }
        Double total = 0.0;
        logger.info("Save Data --> {}", taxDTO);
        return taxService.saveTax(taxDTO);

    }


}
