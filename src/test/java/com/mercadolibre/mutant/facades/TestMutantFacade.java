package com.mercadolibre.mutant.facades;

import com.mercadolibre.mutant.facade.MutantFacade;
import com.mercadolibre.mutant.service.MutantService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TestMutantFacade {

    @InjectMocks
    private MutantFacade mutantFacade;

    @Mock
    private MutantService mutantService;

    @Test
    public void shouldGetTrueForIsMutant() {

        List<String> dnas = new ArrayList<>();
        dnas.add("ATGCGA");
        dnas.add("CAGTGC");
        dnas.add("TTATGT");
        dnas.add("AGAAGG");
        dnas.add("CCCCTA");
        dnas.add("TCACTG");

        Boolean isMutant = mutantFacade.isMutant(dnas);

        Assert.assertTrue(isMutant);
    }

    @Test
    public void shouldGetTrueForIsMutant2() {

        List<String> dnas = new ArrayList<>();
        dnas.add("ATCG");
        dnas.add("CAGG");
        dnas.add("GGGG");
        dnas.add("GAAG");

        Boolean isMutant = mutantFacade.isMutant(dnas);

        Assert.assertTrue(isMutant);
    }


    @Test
    public void shouldGetFalseForIsMutant() {

        List<String> dnas = new ArrayList<>();
        dnas.add("ATGCGA");
        dnas.add("CAGTGC");
        dnas.add("TTATTT");
        dnas.add("AGACGG");
        dnas.add("GCGTCA");
        dnas.add("TCACTG");

        Boolean isMutant = mutantFacade.isMutant(dnas);

        Assert.assertFalse(isMutant);
    }
}
