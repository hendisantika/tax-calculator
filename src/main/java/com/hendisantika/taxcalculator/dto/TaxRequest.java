package com.hendisantika.taxcalculator.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-04
 * Time: 05:34
 * To change this template use File | Settings | File Templates.
 */
@Data
public class TaxRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer taxCode;

    private Double price;

}