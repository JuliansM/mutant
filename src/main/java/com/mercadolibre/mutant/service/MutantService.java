package com.mercadolibre.mutant.service;

import com.mercadolibre.mutant.entity.Dna;
import com.mercadolibre.mutant.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantService {

    @Autowired
    private MutantRepository mutantRepository;

    public Dna save(Dna dna) {
        return mutantRepository.save(dna);
    }

    public List<Dna> getDnas() {
        return mutantRepository.findAll();
    }
}