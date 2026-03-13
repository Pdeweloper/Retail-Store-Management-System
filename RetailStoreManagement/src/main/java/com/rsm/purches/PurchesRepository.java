package com.rsm.purches;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchesRepository extends JpaRepository<PurchesTransaction, Long> {

}
