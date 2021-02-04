package com.taylanunutmaz.marketplace.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_sale_price")
    private Integer totalSalePrice;

    @Column(name = "total_list_price")
    private Integer totalListPrice;

    @ManyToMany(mappedBy = "baskets")
    private Set<Product> products = new HashSet<>();

    @OneToOne(mappedBy = "basket")
    private User user;

    public Basket() {
    }

    public Basket(Long id) {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
