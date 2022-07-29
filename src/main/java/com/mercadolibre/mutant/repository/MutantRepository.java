package com.mercadolibre.mutant.repository;

import com.mercadolibre.mutant.entity.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Dna, Integer> {
}
