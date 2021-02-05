package com.taylanunutmaz.marketplace.repository;

import com.taylanunutmaz.marketplace.model.Cart;
import com.taylanunutmaz.marketplace.model.CartItem;
import com.taylanunutmaz.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public CartItem getByProductAndCart(Product product, Cart cart);

    public Set<CartItem> getByProduct(Product product);
}
