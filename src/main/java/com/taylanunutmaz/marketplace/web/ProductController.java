package com.taylanunutmaz.marketplace.web;

import com.taylanunutmaz.marketplace.model.CartItem;
import com.taylanunutmaz.marketplace.model.Category;
import com.taylanunutmaz.marketplace.model.Product;
import com.taylanunutmaz.marketplace.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@RequestMapping("/products")
public class ProductController {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("productForm", new Product());
        model.addAttribute("categories", categoryRepository.findAll());

        return "products/create";
    }

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @PostMapping
    public String store(@ModelAttribute("productForm") Product productForm, Model model) {
        // validate

        Product product = productRepository.save(productForm);
        product.setCategories(product.getCategories());

        model.addAttribute("product", product);

        return "products/show";
    }

    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_BUYER')")
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());

        return "products/list";
    }

    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_BUYER')")
    @GetMapping("/{product}")
    public String show(@PathVariable(name = "product") Product product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("cartItemForm", new CartItem());
        return "products/show";
    }
}
