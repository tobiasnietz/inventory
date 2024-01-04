package com.thwildau.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Inventory inventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @GetMapping("/delete/{id}")
    public void delelem(@PathVariable Long id) {
        try {
            inventoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new InventoryNotFoundException(id);
        }
    }

    @GetMapping("/inventory/location/{location}")
    List<Inventory> allByLocation(@PathVariable String location) {
        var theProbe = new Inventory();
        theProbe.setLocation(location);
        var theExample = Example.of(theProbe);
        return inventoryRepository.findAll(theExample);
    }
}
