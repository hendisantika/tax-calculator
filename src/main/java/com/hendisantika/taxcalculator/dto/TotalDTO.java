package com.hendisantika.taxcalculator.dto;

import lombok.Data;

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
public class TotalDTO {
    Long id;

    Double priceSubTotal;

    Double taxSubTotal;

    Double grandTotal;
}