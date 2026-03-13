package com.rsm.purches;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rsm.inventory.Inventory;
import com.rsm.inventory.InventoryRepository;

@Service
public class PurchesService {

	private PurchesRepository purchaseRepo;
	private InventoryRepository inventoryRepo;
	public PurchesService(PurchesRepository purchaseRepo, InventoryRepository inventoryRepo) {
		super();
		this.purchaseRepo = purchaseRepo;
		this.inventoryRepo = inventoryRepo;
	}
	
	public PurchesTransaction purchase(PurchesTransaction purchase) {

        purchase.setTotalCost(
            purchase.getUnitCost()
            .multiply(new BigDecimal(purchase.getQuantity()))
        );

        Inventory inventory =
            inventoryRepo.findByProduct(purchase.getProduct())
            .orElseThrow();

        inventory.setCurrentStock(
            inventory.getCurrentStock() + purchase.getQuantity()
        );

        inventoryRepo.save(inventory);

        return purchaseRepo.save(purchase);
    }
	
	public List<PurchesTransaction> getAllPurchases()
	{
		return purchaseRepo.findAll();
	}
}
