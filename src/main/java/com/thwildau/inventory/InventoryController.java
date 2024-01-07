package com.thwildau.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;

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

    @DeleteMapping("/inventory/{id}")
    public void delelem(@PathVariable Long id) {

        inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException(id));

        inventoryRepository.deleteById(id);
    }

    @GetMapping("/inventory/location/{location}")
    List<Inventory> allByLocation(@PathVariable String location) {
        var theProbe = new Inventory();
        theProbe.setLocation(location);
        var theExample = Example.of(theProbe);
        return inventoryRepository.findAll(theExample);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        var updateInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException(id));
        
        updateInventory.setName(inventory.getName());
        updateInventory.setLocation(inventory.getLocation());

        inventoryRepository.save(updateInventory);
        return ResponseEntity.ok(updateInventory);
    }
}
