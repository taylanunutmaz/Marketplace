package com.taylanunutmaz.marketplace.web;

import com.taylanunutmaz.marketplace.model.Category;
import com.taylanunutmaz.marketplace.model.Product;
import com.taylanunutmaz.marketplace.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private CurrencyRepository currencyRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;
    private ProductImageRepository productImageRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductController(CurrencyRepository currencyRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, ProductImageRepository productImageRepository, ProductRepository productRepository) {
        this.currencyRepository = currencyRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("productForm", new Product());
        model.addAttribute("currencies", currencyRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("images", productImageRepository.findAll());

        return "products/create";
    }

    @PostMapping
    public String store(@ModelAttribute("productForm") Product productForm) {
        // validate

        productRepository.save(productForm);

        return "redirect:/welcome";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());

        return "products/list";
    }
}
