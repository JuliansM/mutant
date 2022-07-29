package com.mercadolibre.mutant.facade;

import com.mercadolibre.mutant.entity.Dna;
import com.mercadolibre.mutant.entity.dtos.StatDto;
import com.mercadolibre.mutant.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MutantFacade {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    @Autowired
    private MutantService mutantService;

    public Boolean isMutant(List<String> dnas) {
        char matriz[][] = convertToMatriz(dnas);

        List<String> dnasOfMatriz = getDnas(matriz);
        dnasOfMatriz.addAll(dnas);

        System.out.println("======================= LISTA ADNS PARA VERIFICAR ========================");
        System.out.println(dnasOfMatriz);

        Boolean isMutant = countMutantDna(dnasOfMatriz, dnas.get(0).length()) > 1 ? Boolean.TRUE : Boolean.FALSE;

        saveDna(dnas, isMutant);

        return isMutant;
    }

    private char[][] convertToMatriz(List<String> dnas) {
        int nRows = dnas.size();
        int nFields = dnas.get(0).length();

        char[][] matriz = new char[nRows][nFields];

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nFields; j++) {
                matriz[i][j] = dnas.get(i).charAt(j);
            }
        }

        System.out.println("======================== MATRIZ CONVERTIDA =============================");
        System.out.println(matriz);

        return matriz;
    }

    private List<String> getDnas(char[][] matrizDnas) {
        List<String> dnasColumn = new ArrayList<>();
        List<String> dnasDiagonal = new ArrayList<>();

        char[] diagoPrincipal = new char[matrizDnas.length];
        char[] diagoSecundaria = new char[matrizDnas.length];

        // get Diagonales
        for (int i = 0; i < matrizDnas.length; i++) {
            for (int j = 0; j < matrizDnas[i].length; j++) {

                if (i == j) {
                    diagoPrincipal[i] = matrizDnas[i][j];
                }

                if ((i + j) == matrizDnas.length - 1) {
                    diagoSecundaria[i] = matrizDnas[i][j];
                }

            }
        }

        dnasDiagonal.add(Arrays.toString(diagoPrincipal)
                .trim()
                .replace("[","")
                .replace("]","")
                .replace(",","")
                .replace(" ",""));

        dnasDiagonal.add(Arrays.toString(diagoSecundaria)
                .trim()
                .replace("[","")
                .replace("]","")
                .replace(",","")
                .replace(" ",""));

        // get columnas
        for (int j = 0; j < matrizDnas.length; j++) {
            char[] columns = new char[matrizDnas.length];

            for (int i = 0; i < matrizDnas[j].length; i++) {
                columns[i] = matrizDnas[i][j];
            }

            dnasColumn.add(Arrays.toString(columns)
                    .trim()
                    .replace("[","")
                    .replace("]","")
                    .replace(",","")
                    .replace(" ",""));
        }

        List<String> dnas = new ArrayList<>();
        dnas.addAll(dnasColumn);
        dnas.addAll(dnasDiagonal);

        return dnas;
    }

    private int countMutantDna(List<String> dnas, int nFields) {

        List<String> dnasMutant = new ArrayList<>();
        int counter = 0;

        for (int i = 0; i < dnas.size(); i++) {

            for (int j = 0; j < nFields; j++) {

                if (j < nFields - 1) {
                    if (dnas.get(i).charAt(j) == dnas.get(i).charAt(j+1)) {
                        counter++;
                        if (counter == 3) {
                            counter++;
                        }
                    } else {
                        if (counter < 4) {
                            counter = 0;
                        }
                    }
                }
            }

            if (counter >= 4) {
                dnasMutant.add(dnas.get(i));
            }
        }

        return dnasMutant.size();
    }

    private void saveDna(List<String> dnas, Boolean isMutant) {
        String d = dnas.toString()
                .trim()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");

        Dna dna = new Dna();
        dna.setDna(d);
        dna.setIsMutant(isMutant);
        dna.setCreatedAt(LocalDateTime.now());

        mutantService.save(dna);
    }

    public StatDto getStats() {
        StatDto statDto = new StatDto();

        List<Dna> dnas = mutantService.getDnas();

        statDto.setCountHumanDna(dnas.size());

        Integer countMutantDna = Math.toIntExact(dnas.stream()
                .map(Dna::getIsMutant)
                .filter(d -> d.equals(Boolean.TRUE))
                .count());

        statDto.setCountMutantDna(countMutantDna);

        Double ratio = (double) statDto.getCountMutantDna() / (double) statDto.getCountHumanDna();

        decimalFormat.setRoundingMode(RoundingMode.UP);
        statDto.setRatio(Double.valueOf(decimalFormat.format(ratio).replace(",", ".")));

        return statDto;
    }
}
