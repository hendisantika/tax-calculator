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
 * Time: 05:36
 * To change this template use File | Settings | File Templates.
 */
@Data
public class TotalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    Long id;

    Double priceSubTotal;

    Double taxSubTotal;

    Double grandTotal;
}