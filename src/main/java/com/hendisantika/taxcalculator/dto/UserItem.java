package com.hendisantika.taxcalculator.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-06
 * Time: 09:49
 * To change this template use File | Settings | File Templates.
 */

@Data
public class UserItem {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String userId;
    List<TaxResponse> items = new ArrayList<>();
    TotalDTO totalDTO;
}
