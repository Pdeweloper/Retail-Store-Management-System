package com.rsm.purches;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rsm.party.Party;
import com.rsm.product.Product;
import com.rsm.users.User;

import jakarta.persistence.*;

@Entity
@Table(name="purchase_tb")
public class PurchesTransaction {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true)
	    private String transactionNumber;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    @ManyToOne
	    @JoinColumn(name = "party_id")
	    private Party party;

	    private Integer quantity;
	    private BigDecimal unitCost;
	    private BigDecimal totalCost;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	    private LocalDateTime transactionDate = LocalDateTime.now();

	    @ManyToOne
	    @JoinColumn(name = "created_by")
	    private User createdBy;

	    //getter and setter
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTransactionNumber() {
			return transactionNumber;
		}

		public void setTransactionNumber(String transactionNumber) {
			this.transactionNumber = transactionNumber;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Party getParty() {
			return party;
		}

		public void setParty(Party party) {
			this.party = party;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public BigDecimal getUnitCost() {
			return unitCost;
		}

		public void setUnitCost(BigDecimal unitCost) {
			this.unitCost = unitCost;
		}

		public BigDecimal getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(BigDecimal totalCost) {
			this.totalCost = totalCost;
		}

		public LocalDateTime getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDate(LocalDateTime transactionDate) {
			this.transactionDate = transactionDate;
		}

		public User getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(User createdBy) {
			this.createdBy = createdBy;
		}
	    
}
