package com.hendisantika.taxcalculator.dto;

import lombok.Data;

import javax.persistence.Id;
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
    List<TaxDTO> items = new ArrayList<>();
    TotalDTO totalDTO;
    @Id
    private String id;
    private String userId;
}
