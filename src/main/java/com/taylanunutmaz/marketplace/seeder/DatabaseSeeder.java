package com.taylanunutmaz.marketplace.seeder;

import com.taylanunutmaz.marketplace.model.Category;
import com.taylanunutmaz.marketplace.model.Currency;
import com.taylanunutmaz.marketplace.model.Role;
import com.taylanunutmaz.marketplace.repository.CategoryRepository;
import com.taylanunutmaz.marketplace.repository.CurrencyRepository;
import com.taylanunutmaz.marketplace.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private RoleRepository roleRepository;
    private CurrencyRepository currencyRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DatabaseSeeder(RoleRepository roleRepository, CurrencyRepository currencyRepository, CategoryRepository categoryRepository) {
        this.currencyRepository = currencyRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedCurrencyTable();
        seedCategoryTable();
    }

    private void seedRoleTable() {
        if (roleRepository.count() == 0) {
            Role buyerRole = new Role("ROLE_BUYER", "Buyer");
            Role sellerRole = new Role("ROLE_SELLER", "Seller");
            roleRepository.save(buyerRole);
            roleRepository.save(sellerRole);
            logger.info("Role Table Seeded");
        }
    }

    private void seedCurrencyTable() {
        if (currencyRepository.count() == 0) {
            Currency currencyTry = new Currency("TRY", "₺");
            Currency currencyUsd = new Currency("USD", "$");
            Currency currencyEur = new Currency("EUR", "€");
            currencyRepository.save(currencyTry);
            currencyRepository.save(currencyUsd);
            currencyRepository.save(currencyEur);
            logger.info("Currency Table Seeded");
        }
    }

    private void seedCategoryTable() {
        if (categoryRepository.count() == 0) {
            Category categoryMan = new Category("Man");
            Category categoryWoman = new Category("Woman");
            categoryRepository.save(categoryMan);
            categoryRepository.save(categoryWoman);
            logger.info("Currency Table Seeded");
        }
    }
}
