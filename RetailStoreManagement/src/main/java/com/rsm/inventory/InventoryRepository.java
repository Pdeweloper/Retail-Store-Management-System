package com.rsm.inventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsm.product.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findByProduct(Product product);
	Optional<Inventory> findByProductId(Long productId);
}
