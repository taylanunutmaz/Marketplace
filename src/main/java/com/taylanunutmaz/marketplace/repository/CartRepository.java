package com.taylanunutmaz.marketplace.repository;

import com.taylanunutmaz.marketplace.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
