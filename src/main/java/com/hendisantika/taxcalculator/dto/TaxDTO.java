package com.hendisantika.taxcalculator.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
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
public class TaxDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private Integer taxCode;

    private String type;

    private Boolean refundable;

    private Double price;

    private Double tax;

    private Double amount;
}