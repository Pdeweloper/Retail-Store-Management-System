package com.rsm.inventory;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rsm.product.Product;

import jakarta.persistence.*;

@Entity
@Table(name="inventory_tb")
public class Inventory {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    @Column(nullable = false)
	    private Integer currentStock;

	    private Integer minimumStock;
	    
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	    private LocalDateTime lastUpdated = LocalDateTime.now();
	    
	    //getter and setter
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public Integer getCurrentStock() {
			return currentStock;
		}
		public void setCurrentStock(Integer currentStock) {
			this.currentStock = currentStock;
		}
		public Integer getMinimumStock() {
			return minimumStock;
		}
		public void setMinimumStock(Integer minimumStock) {
			this.minimumStock = minimumStock;
		}
		public LocalDateTime getLastUpdated() {
			return lastUpdated;
		}
		public void setLastUpdated(LocalDateTime lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
	
	    
}
