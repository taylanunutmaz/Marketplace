package com.taylanunutmaz.marketplace.model;

import javax.persistence.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

@Entity
@Table(name = "cart_item")
public class CartItem extends Observable implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public CartItem() {
    }

    public CartItem(Integer quantity, Cart cart, Product product) {
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void update(Observable o, Object arg) {
        Product product = (Product) o;
        Set<CartItem> cartItems = product.getCartItems();

        for (CartItem cartItem : cartItems) {
            cartItem.addObserver(cartItem.cart);
        }

        setChanged();
        notifyObservers(o);
    }
}
