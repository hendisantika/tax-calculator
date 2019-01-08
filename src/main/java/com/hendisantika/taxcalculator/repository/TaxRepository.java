package com.hendisantika.taxcalculator.repository;

import com.hendisantika.taxcalculator.domain.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface TaxRepository extends JpaRepository<Tax, Long> {

    Tax save(Tax tax);

    Page<Tax> findAll(Pageable pageable);

    Tax findByTaxCode(Integer taxCode);
}
