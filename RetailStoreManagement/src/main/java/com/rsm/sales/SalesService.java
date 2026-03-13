package com.rsm.sales;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rsm.inventory.Inventory;
import com.rsm.inventory.InventoryRepository;
import com.rsm.product.Product;

@Service
public class SalesService {

	private SalesRepository salesRepo;
	private InventoryRepository inventoryRepo;
	public SalesService(SalesRepository salesRepo, InventoryRepository inventoryRepo) {
		super();
		this.salesRepo = salesRepo;
		this.inventoryRepo = inventoryRepo;
	}
	
	public SalesTransaction sell(SalesTransaction sale) {

	    // 1️⃣ Fetch product from DB
	    Product product = inventoryRepo.findByProduct(sale.getProduct())
	            .orElseThrow()
	            .getProduct();

	    // 2️⃣ Fetch inventory
	    Inventory inventory =
	            inventoryRepo.findByProduct(product)
	            .orElseThrow(() -> new RuntimeException("Inventory not found"));

	    if (inventory.getCurrentStock() < sale.getQuantity()) {
	        throw new RuntimeException("Insufficient stock");
	    }

	    // 3️⃣ Safe values
	    BigDecimal unitPrice = sale.getUnitPrice();
	    BigDecimal taxPercent = product.getTaxPercent();

	    if (unitPrice == null)
	        throw new RuntimeException("Unit price required");

	    if (taxPercent == null)
	        taxPercent = BigDecimal.ZERO;

	    // 4️⃣ Calculate
	    BigDecimal tax = unitPrice
	            .multiply(taxPercent)
	            .divide(new BigDecimal("100"));

	    BigDecimal total = unitPrice
	            .multiply(BigDecimal.valueOf(sale.getQuantity()))
	            .add(tax);

	    sale.setProduct(product);
	    sale.setTaxAmount(tax);
	    sale.setTotalAmount(total);

	    // 5️⃣ Reduce stock
	    inventory.setCurrentStock(
	            inventory.getCurrentStock() - sale.getQuantity()
	    );

	    inventoryRepo.save(inventory);

	    return salesRepo.save(sale);
	}
	
	public List<SalesTransaction> getAllSales()
	{
		return salesRepo.findAll();
	}
}
