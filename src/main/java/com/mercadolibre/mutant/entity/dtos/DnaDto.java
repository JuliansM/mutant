package com.mercadolibre.mutant.entity.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class DnaDto implements Serializable {

    List<String> dnas;
}
