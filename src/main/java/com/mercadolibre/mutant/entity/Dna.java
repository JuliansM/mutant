package com.mercadolibre.mutant.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "dnas", schema = "public")
public class Dna {

    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "dna", nullable = false)
    private String dna;

    @NotNull
    @Column(name = "is_mutant", nullable = false)
    private Boolean isMutant;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
