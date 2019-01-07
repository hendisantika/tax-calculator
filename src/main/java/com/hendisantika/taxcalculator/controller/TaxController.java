package com.hendisantika.taxcalculator.controller;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.dto.TaxRequest;
import com.hendisantika.taxcalculator.dto.UserItem;
import com.hendisantika.taxcalculator.service.TaxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "tax-controller", description = "Endpoints for handling and managing user items tax related operations", tags = "/tax")
public class TaxController {
    private static Logger logger = LoggerFactory.getLogger(TaxController.class);

    @Autowired
    TaxService taxService;

    @GetMapping
    @ApiOperation(value = "Get All Tax List", response = Tax.class)
    Page<Tax> getTaxList(Pageable pageable) {
        return taxService.getTaxList(pageable);
    }

    @PostMapping("add")
    @ApiOperation(value = "Add new Tax Item List", response = UserItem.class)
    UserItem addTaxItemList(@RequestBody TaxRequest taxRequest, @RequestParam(value = "requestId", required = false) final String requestId) {
        return taxService.addTaxItem(taxRequest, requestId);
    }

}
