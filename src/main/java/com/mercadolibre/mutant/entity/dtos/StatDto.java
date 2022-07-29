package com.mercadolibre.mutant.entity.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@NoArgsConstructor
public class StatDto {

    private Integer countMutantDna;
    private Integer countHumanDna;
    private Double ratio;
}
