package com.thwildau.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(InventoryRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Inventory("Product 1", "Berlin")));
            log.info("Preloading " + repository.save(new Inventory("Product 2", "Dresden")));
        };
    }
}
