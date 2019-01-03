package com.hendisantika.taxcalculator.service;

import com.hendisantika.taxcalculator.domain.Tax;
import com.hendisantika.taxcalculator.dto.TaxDTO;
import com.hendisantika.taxcalculator.dto.TotalDTO;
import com.hendisantika.taxcalculator.repository.TaxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    private static List<TaxDTO> taxDTOList = new ArrayList<>();

    private static List<TaxDTO> taxDTOListTemp = new ArrayList<>();

    private static Map<String, List<TaxDTO>> uid = new HashMap<>();

    @Autowired
    TaxRepository taxRepository;

    public Tax saveTax(TaxDTO taxDTO) {

        Tax tax = new Tax();
        Integer type = taxDTO.getTaxCode();
        switch (type) {
            case 1:
                taxDTO.setType("Food & Beverage");
                taxDTO.setRefundable(true);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;
            case 2:
                taxDTO.setType("Tobacco");
                taxDTO.setRefundable(false);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;

            case 3:
                taxDTO.setType("Entertainment");
                taxDTO.setRefundable(false);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;
        }
        log.info("Data tax --> {}", taxDTO);
        BeanUtils.copyProperties(taxDTO, tax);

        return taxRepository.save(tax);
    }

    public List<TaxDTO> addItemTax(TaxDTO taxDTO) {
        UUID uuid = UUID.randomUUID();
        Integer type = taxDTO.getTaxCode();
        switch (type) {
            case 1:
                taxDTO.setType("Food & Beverage");
                taxDTO.setRefundable(true);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;
            case 2:
                taxDTO.setType("Tobacco");
                taxDTO.setRefundable(false);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;

            case 3:
                taxDTO.setType("Entertainment");
                taxDTO.setRefundable(false);
                taxDTO.setTax(countTax(taxDTO.getPrice(), taxDTO.getTaxCode()));
                taxDTO.setAmount(sumAmount(taxDTO.getPrice(), taxDTO.getTax()));
                break;
        }
        taxDTOList.add(taxDTO);
        log.info("Data tax --> {}", taxDTO);
        taxDTOList.forEach(System.out::println);
        log.info("Tax Item List Data --> {}", taxDTOList.size());
        return taxDTOList;
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

//    public List<TaxDTO> getTaxList(){
//        List<Tax> taxList = (List) taxRepository.findAll();
//        return taxList.stream().map(entity -> {
//            final TaxDTO dto = new TaxDTO();
//            BeanUtils.copyProperties(entity, dto);
//            return dto;
//        }).collect(Collectors.toList());
//    }

    public List<Tax> getTaxList() {
        return taxRepository.findAll();
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
