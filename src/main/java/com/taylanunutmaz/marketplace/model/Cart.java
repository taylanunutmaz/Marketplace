package com.taylanunutmaz.marketplace.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_sale_price")
    private Integer totalSalePrice;

    @Column(name = "total_list_price")
    private Integer totalListPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToOne(mappedBy = "cart")
    private User user;

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(Integer totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public Integer getTotalListPrice() {
        return totalListPrice;
    }

    public void setTotalListPrice(Integer totalListPrice) {
        this.totalListPrice = totalListPrice;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void update(Observable o, Object arg) {
        Integer sumOfListPrice = 0;
        Integer sumOfSalePrice = 0;

        for (CartItem cartItem : cartItems) {
            sumOfListPrice += cartItem.getProduct().getListPrice();
            sumOfSalePrice += cartItem.getProduct().getSalePrice();
        }

        setTotalSalePrice(sumOfSalePrice);
        setTotalListPrice(sumOfListPrice);

        System.out.println(sumOfSalePrice);
        System.out.println(getTotalSalePrice());
    }
}
