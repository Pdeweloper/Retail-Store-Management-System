package com.rsm.inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsm.product.Product;
import com.rsm.product.ProductRepository;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;
	
	@Autowired
	private ProductRepository productrepo;
	
	@GetMapping
    public List<Inventory> getAllInventory() {
        return service.getAllInventory();
    }
	
	@GetMapping("/by-product/{id}")
	public Inventory productInv(@PathVariable Long id)
	{
		return service.getByProductId(id);
	}

	//can update like this "http://localhost:8080/api/inventory/1/minimum-stock?minimumStock=50"
    @PutMapping("/{id}/minimum-stock")
    public Inventory updateMinimumStock(
            @PathVariable Long id,
            @RequestParam Integer minimumStock) {

        return service.updateMinimumStock(id, minimumStock);
    }

    @PutMapping("/{id}/adjust-stock")
    public Inventory adjustStock(
            @PathVariable Long id,
            @RequestParam Integer newStock) {

        return service.adjustStock(id, newStock);
    }

    @GetMapping("/low-stock")
    public List<Inventory> getLowStockItems() {
        return service.getLowStockItems();
    }
}
