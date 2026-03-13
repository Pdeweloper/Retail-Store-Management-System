package com.rsm.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsm.product.Product;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepo;

	    // Get all inventory
	    public List<Inventory> getAllInventory() {
	        return inventoryRepo.findAll();
	    }
	    
	 // Get inventory by product ID
	    public Inventory getByProductId(Long productId) {
	        return inventoryRepo.findByProductId(productId)
	                .orElseThrow(() -> new RuntimeException("Inventory not found for product ID: " + productId));
	    }

	    // Update minimum stock level
	    public Inventory updateMinimumStock(Long inventoryId, Integer minimumStock) {
	        Inventory inventory = inventoryRepo.findById(inventoryId)
	                .orElseThrow(() -> new RuntimeException("Inventory not found"));

	        inventory.setMinimumStock(minimumStock);
	        return inventoryRepo.save(inventory);
	    }

	    // Manual stock adjustment (ADMIN use only)
	    public Inventory adjustStock(Long inventoryId, Integer newStock) {
	        Inventory inventory = inventoryRepo.findById(inventoryId)
	                .orElseThrow(() -> new RuntimeException("Inventory not found"));

	        inventory.setCurrentStock(newStock);
	        return inventoryRepo.save(inventory);
	    }

	    // Low stock check
	    public List<Inventory> getLowStockItems() {
	        return inventoryRepo.findAll()
	                .stream()
	                .filter(inv -> inv.getCurrentStock() <= inv.getMinimumStock())
	                .toList();
	    }
}
