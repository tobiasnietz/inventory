package com.thwildau.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
