package com.thwildau.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    List<Inventory> all() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/inventory/{id}")
    Inventory one(@PathVariable Long id) {

        return inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException(id));
    }

    @PostMapping("/inventory")
    public Inventory inventory(@RequestBody Inventory inventory){
        return inventoryRepository.save(inventory);
    }

}
