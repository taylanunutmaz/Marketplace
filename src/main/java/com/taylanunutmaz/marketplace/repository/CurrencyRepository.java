package com.taylanunutmaz.marketplace.repository;

import com.taylanunutmaz.marketplace.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
