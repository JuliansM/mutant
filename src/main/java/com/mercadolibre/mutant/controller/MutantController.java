package com.mercadolibre.mutant.controller;

import com.mercadolibre.mutant.entity.dtos.DnaDto;
import com.mercadolibre.mutant.entity.dtos.StatDto;
import com.mercadolibre.mutant.facade.MutantFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mutant")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class MutantController {

    @Autowired
    private MutantFacade mutantFacade;

    @PostMapping
    public ResponseEntity<?> verifyMutantHuman(@RequestBody DnaDto dnaDto) {

        try {
            Boolean isMutant = mutantFacade.isMutant(dnaDto.getDnas());

            if (isMutant) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
        StatDto statDto = mutantFacade.getStats();
        return new ResponseEntity<>(statDto, HttpStatus.OK);
    }
}
