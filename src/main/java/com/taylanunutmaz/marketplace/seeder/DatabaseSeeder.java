package com.taylanunutmaz.marketplace.seeder;

import com.taylanunutmaz.marketplace.model.Role;
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

    @Autowired
    public DatabaseSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRolesTable();
    }

    private void seedRolesTable() {
        if (roleRepository.count() == 0) {
            Role buyerRole = new Role("ROLE_BUYER", "Buyer");
            Role sellerRole = new Role("ROLE_SELLER", "Seller");
            roleRepository.save(buyerRole);
            roleRepository.save(sellerRole);
            logger.info("Roles Table Seeded");
        }
    }
}
