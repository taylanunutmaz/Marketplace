package com.taylanunutmaz.marketplace.web;

import com.taylanunutmaz.marketplace.model.Cart;
import com.taylanunutmaz.marketplace.model.CartItem;
import com.taylanunutmaz.marketplace.model.Product;
import com.taylanunutmaz.marketplace.model.User;
import com.taylanunutmaz.marketplace.repository.CartItemRepository;
import com.taylanunutmaz.marketplace.repository.CartRepository;
import com.taylanunutmaz.marketplace.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private EntityManager entityManager;

    @Autowired
    public CartController(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.entityManager = entityManager;
    }

    @GetMapping
    public String list(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());

        model.addAttribute("cart", user.getCart());

        return "cart/list";
    }

    @PostMapping
    public String store(@RequestParam Product product, @RequestParam Integer quantity, Principal principal, Model model) throws Exception {
        // validate

        User user = userRepository.findByUsername(principal.getName());
        Cart cart = user.getCart();

        CartItem cartItem = cartItemRepository.getByProductAndCart(product, cart);

        if (cartItem == null) {
            cartItem = new CartItem(quantity, cart, product);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        Integer sumOfListPrice = 0;
        Integer sumOfSalePrice = 0;

        for (CartItem item : cart.getCartItems()) {
            sumOfListPrice += cartItem.getProduct().getListPrice() * cartItem.getQuantity();
            sumOfSalePrice += cartItem.getProduct().getSalePrice() * cartItem.getQuantity();
        }

        cart.setTotalSalePrice(sumOfSalePrice);
        cart.setTotalListPrice(sumOfListPrice);

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        model.addAttribute("cart", cart);

        return "cart/list";
    }
}
