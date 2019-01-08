package com.hendisantika.taxcalculator.service;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.dto.TaxRequest;
import com.hendisantika.taxcalculator.dto.TaxResponse;
import com.hendisantika.taxcalculator.dto.TotalDTO;
import com.hendisantika.taxcalculator.dto.UserItem;
import com.hendisantika.taxcalculator.repository.TaxRepository;
import com.hendisantika.taxcalculator.utils.RequestIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-04
 * Time: 05:37
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class TaxService {
    private static Logger log = LoggerFactory.getLogger(TaxService.class);

    private static Map<String, List<TaxResponse>> reqMap = new HashMap<>();

    @Autowired
    TaxRepository taxRepository;

    public Tax saveTax(Tax tax) {
        log.info("Data tax --> {}", tax);
        return taxRepository.save(tax);
    }

    public UserItem addTaxItem(TaxRequest taxRequest, String requestId) {
        UserItem userItem = new UserItem();
        Tax tax = new Tax();
        List<TaxResponse> taxDTOListTemp = new ArrayList<>();
        if (requestId == null) {
            requestId = RequestIDGenerator.getID();
            Integer type = taxRequest.getTaxCode();
            TaxResponse taxResponse = checkType(taxRequest, type);

            taxDTOListTemp.add(taxResponse);
            prepareSaveData(taxResponse, requestId, userItem, tax, taxDTOListTemp);
        } else {
            Integer type = taxRequest.getTaxCode();
            TaxResponse taxResponse = checkType(taxRequest, type);
            if (reqMap.containsKey(requestId)) {
                taxDTOListTemp = reqMap.get(requestId);
                taxDTOListTemp.add(taxResponse);
                prepareSaveData(taxResponse, requestId, userItem, tax, taxDTOListTemp);
            }
        }

        return userItem;
    }

    private void prepareSaveData(TaxResponse taxDTO, String requestId, UserItem userItem, Tax tax, List<TaxResponse> taxDTOListTemp) {
        TotalDTO totalDTO;
        totalDTO = countAll(taxDTOListTemp);
        setReqMap(userItem, requestId, taxDTOListTemp, totalDTO);
        tax.setUserId(requestId);
        tax.setTaxCode(taxDTO.getTaxCode());
        tax.setName(taxDTO.getName());
        tax.setPrice(taxDTO.getPrice());
        saveTax(tax);
    }

    private void setReqMap(UserItem userItem, String requestId, List<TaxResponse> taxDTOListTemp, TotalDTO totalDTO) {
        reqMap.put(requestId, taxDTOListTemp);
        reqMap.forEach((k, v) -> log.info("UUID : " + k + " Data : " + v));
        log.info("Jumlah Data --> {}", reqMap.size());
        reqMap.forEach((k, v) -> {
            log.info("Item : " + k + " Count : " + v);
            userItem.setId(RequestIDGenerator.getID());
            userItem.setItems(taxDTOListTemp);
            userItem.setUserId(requestId);
            userItem.setTotalDTO(totalDTO);
        });
        log.info("User Item --> {}", userItem);
    }

    private TaxResponse checkType(TaxRequest taxRequest, Integer type) {
        TaxResponse taxResponse = new TaxResponse();
        switch (type) {
            case 1:
                taxResponse.setId(RequestIDGenerator.getID());
                taxResponse.setName(taxRequest.getName());
                taxResponse.setTaxCode(taxRequest.getTaxCode());
                taxResponse.setPrice(taxRequest.getPrice());
                taxResponse.setType("Food & Beverage");
                taxResponse.setRefundable(true);
                taxResponse.setTax(countTax(taxRequest.getPrice(), taxRequest.getTaxCode()));
                taxResponse.setAmount(sumAmount(taxRequest.getPrice(), taxResponse.getTax()));
                break;
            case 2:
                taxResponse.setId(RequestIDGenerator.getID());
                taxResponse.setName(taxRequest.getName());
                taxResponse.setTaxCode(taxRequest.getTaxCode());
                taxResponse.setPrice(taxRequest.getPrice());
                taxResponse.setType("Tobacco");
                taxResponse.setRefundable(false);
                taxResponse.setTax(countTax(taxRequest.getPrice(), taxRequest.getTaxCode()));
                taxResponse.setAmount(sumAmount(taxRequest.getPrice(), taxResponse.getTax()));
                break;

            case 3:
                taxResponse.setId(RequestIDGenerator.getID());
                taxResponse.setName(taxRequest.getName());
                taxResponse.setTaxCode(taxRequest.getTaxCode());
                taxResponse.setPrice(taxRequest.getPrice());
                taxResponse.setType("Entertainment");
                taxResponse.setRefundable(false);
                taxResponse.setTax(countTax(taxRequest.getPrice(), taxRequest.getTaxCode()));
                taxResponse.setAmount(sumAmount(taxRequest.getPrice(), taxResponse.getTax()));
                break;
        }
        return taxResponse;
    }


    private TotalDTO countAll(List<TaxResponse> taxDTOList) {
        TotalDTO totalDTO = new TotalDTO();
        totalDTO.setPriceSubTotal(calculatePriceSubTotal(taxDTOList));
        totalDTO.setTaxSubTotal(calculateTaxSubTotal(taxDTOList));
        totalDTO.setGrandTotal(calculateGrandTotal(taxDTOList));

        log.info("Total Amount -- > {}", totalDTO);

        return totalDTO;
    }

    private Double sumAmount(Double price, Double tax) {
        return price + tax;
    }

    private Double calculatePriceSubTotal(List<TaxResponse> taxDTOList) {
        Double priceSubTotal = 0.0;
        for (TaxResponse temp : taxDTOList) {
            priceSubTotal = priceSubTotal + temp.getAmount();
        }
        return priceSubTotal;
    }


    private Double calculateTaxSubTotal(List<TaxResponse> taxDTOList) {
        Double taxSubTotal = 0.0;
        for (TaxResponse temp : taxDTOList) {
            taxSubTotal = taxSubTotal + temp.getTax();
        }
        return taxSubTotal;
    }

    private Double calculateGrandTotal(List<TaxResponse> taxDTOList) {
        Double grandTotal = 0.0;
        for (TaxResponse temp : taxDTOList) {
            grandTotal = grandTotal + temp.getPrice();
        }
        return grandTotal;
    }

    public Page<Tax> getTaxList(Pageable pageable) {
        return taxRepository.findAll(pageable);
    }

    public Page<TaxRequest> getAllTaxItems(Pageable pageable) {
        Page<Tax> taxes = taxRepository.findAll(pageable);
        Page<TaxRequest> result = new PageImpl<>(new ArrayList<>(), pageable, 0);
        final List<Tax> content = taxes.getContent();
        final List<TaxRequest> dtoList = content.stream().map(entity -> {
            final TaxRequest taxRequest = new TaxRequest();
            BeanUtils.copyProperties(entity, taxRequest);
            return taxRequest;
        }).collect(Collectors.toList());
        if (!dtoList.isEmpty()) {
            result = new PageImpl<>(dtoList, pageable, taxes.getTotalElements());
        }

        return result;
    }

    private Double countTax(Double price, Integer type) {
        Double result = 0.0;
        switch (type) {
            case 1:
                result = price * 0.1;
                break;
            case 2:
                result = price * 0.02;
                break;
            case 3:
                result = (price < 100) ? (0.0) : (price - 100) * 0.01;
                break;
        }
        return result;
    }

    public Tax findByTaxCode(Integer taxCode) {
        return taxRepository.findByTaxCode(taxCode);
    }

}
