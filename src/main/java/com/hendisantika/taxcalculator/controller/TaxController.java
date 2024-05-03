package com.hendisantika.taxcalculator.controller;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.dto.TaxRequest;
import com.hendisantika.taxcalculator.dto.UserItem;
import com.hendisantika.taxcalculator.service.TaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
@RestController
@RequestMapping("tax")
@RequiredArgsConstructor
@Tag(name = "Tax", description = "Endpoints for handling and managing user items tax related operations")
public class TaxController {

    private final TaxService taxService;

    @GetMapping
    @Operation(summary = "Get All Tax List")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Tax.class))}),

                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Tax.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Tax.class))}),

            }
    )
    @Parameters(
            {
                    @Parameter(name = "page", description = "Page Number"),
                    @Parameter(name = "size", description = "Page Size")
            }
    )
    Page<Tax> getTaxList(Pageable pageable) {
        return taxService.getAllTaxItems(pageable);
    }

    @PostMapping("add")
    @Operation(summary = "Add new Tax Item List")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserItem.class))}),

                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserItem.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserItem.class))}),

            }
    )
    UserItem addTaxItemList(@RequestBody TaxRequest taxRequest, @RequestParam(value = "requestId", required = false) final String requestId) {
        return taxService.addTaxItem(taxRequest, requestId);
    }

}
