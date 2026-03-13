package com.rsm.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rsm.inventory.Inventory;
import com.rsm.inventory.InventoryRepository;

@Service
public class ProductService {

	private ProductRepository productrepo;
	private InventoryRepository inventoryrepo;
	
	public ProductService(ProductRepository productrepo, InventoryRepository inventoryrepo) {
		super();
		this.productrepo = productrepo;
		this.inventoryrepo = inventoryrepo;
	}
	
	//this will save the product in prduct_tb and inventory_tb
	public Product save(Product product)
	{
		Product saveproduct = productrepo.save(product);
		
		Inventory inventory = new Inventory();
		
		inventory.setProduct(saveproduct);
		inventory.setCurrentStock(0);
		
		inventoryrepo.save(inventory);
		
		return saveproduct;
	}
	
	//it will give all product
	public List<Product> getAllProduct()
	{
		return productrepo.findAll();
	}
}
