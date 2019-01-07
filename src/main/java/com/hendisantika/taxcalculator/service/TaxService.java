package com.hendisantika.taxcalculator.service;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.dto.TaxDTO;
import com.hendisantika.taxcalculator.dto.TotalDTO;
import com.hendisantika.taxcalculator.dto.UserItem;
import com.hendisantika.taxcalculator.repository.TaxRepository;
import com.hendisantika.taxcalculator.utils.RequestIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static Map<String, List<TaxDTO>> reqMap = new HashMap<>();

    @Autowired
    TaxRepository taxRepository;

    public Tax saveTax(Tax tax) {
        log.info("Data tax --> {}", tax);
        return taxRepository.save(tax);
    }

    public UserItem addTaxItem(TaxDTO taxDTO, String requestId) {
        UserItem userItem = new UserItem();
        TotalDTO totalDTO;
        Tax tax = new Tax();
        List<TaxDTO> taxDTOListTemp = new ArrayList<>();
        if (requestId == null) {
            requestId = RequestIDGenerator.getID();
            Integer type = taxDTO.getTaxCode();
            checkType(taxDTO, type);

            taxDTOListTemp.add(taxDTO);
            prepareSaveData(taxDTO, requestId, userItem, tax, taxDTOListTemp);
        } else {
            Integer type = taxDTO.getTaxCode();
            checkType(taxDTO, type);
            if (reqMap.containsKey(requestId)) {
                taxDTOListTemp = reqMap.get(requestId);
                taxDTOListTemp.add(taxDTO);
                prepareSaveData(taxDTO, requestId, userItem, tax, taxDTOListTemp);
            }
        }

        return userItem;
    }

    private void prepareSaveData(TaxDTO taxDTO, String requestId, UserItem userItem, Tax tax, List<TaxDTO> taxDTOListTemp) {
        TotalDTO totalDTO;
        totalDTO = countAll(taxDTOListTemp);
        setReqMap(userItem, requestId, taxDTOListTemp, totalDTO);
        tax.setUserId(requestId);
        tax.setTaxCode(taxDTO.getTaxCode());
        tax.setName(taxDTO.getName());
        tax.setPrice(taxDTO.getPrice());
        saveTax(tax);
    }

    private void setReqMap(UserItem userItem, String requestId, List<TaxDTO> taxDTOListTemp, TotalDTO totalDTO) {
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

    private void checkType(TaxDTO taxDTO, Integer type) {
        switch (type) {
            case 1:
                taxDTO.setId(RequestIDGenerator.getID());
                taxDTO.setType("Food & Beverage");
                taxDTO.setRefundable(true);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;
            case 2:
                taxDTO.setId(RequestIDGenerator.getID());
                taxDTO.setType("Tobacco");
                taxDTO.setRefundable(false);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;

            case 3:
                taxDTO.setId(RequestIDGenerator.getID());
                taxDTO.setType("Entertainment");
                taxDTO.setRefundable(false);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;
        }
    }


    private TotalDTO countAll(List<TaxDTO> taxDTOList) {
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

    private Double calculatePriceSubTotal(List<TaxDTO> taxDTOList) {
        Double priceSubTotal = 0.0;
        for (TaxDTO temp : taxDTOList) {
            priceSubTotal = priceSubTotal + temp.getAmount();
        }
        return priceSubTotal;
    }


    private Double calculateTaxSubTotal(List<TaxDTO> taxDTOList) {
        Double taxSubTotal = 0.0;
        for (TaxDTO temp : taxDTOList) {
            taxSubTotal = taxSubTotal + temp.getTax();
        }
        return taxSubTotal;
    }

    private Double calculateGrandTotal(List<TaxDTO> taxDTOList) {
        Double grandTotal = 0.0;
        for (TaxDTO temp : taxDTOList) {
            grandTotal = grandTotal + temp.getPrice();
        }
        return grandTotal;
    }

    public Page<Tax> getTaxList(Pageable pageable) {
        return taxRepository.findAll(pageable);
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

}
