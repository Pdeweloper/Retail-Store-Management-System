package com.rsm.party;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {

	Optional<Party> findById(Long id);
}
