package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.ProcedureAndMedicamentDao;
import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.ProcedureAndMedicamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcedureAndMedicamentServiceImplTest {
    private static final List<ProcedureAndMedicament> PROMED_LIST;

    @Mock
    private ProcedureAndMedicamentDao promedDao;
    @InjectMocks
    private ProcedureAndMedicamentService promedService = new ProcedureAndMedicamentServiceImpl();

    static {
        PROMED_LIST = Arrays.asList(
                ProcedureAndMedicament.builder().kind("Procedure").name("Proc1").build(),
                ProcedureAndMedicament.builder().kind("Medicament").name("Med1").build(),
                ProcedureAndMedicament.builder().kind("Procedure").name("Proc2").build(),
                ProcedureAndMedicament.builder().kind("Medicament").name("Med2").build(),
                ProcedureAndMedicament.builder().kind("Medicament").name("Med3").build()
        );
    }

    @BeforeEach
    void setup() {
        when(promedDao.getProceduresAndMedicines()).thenReturn(PROMED_LIST);
    }

    @Test
    void should_getOnlyProcedures_whenCallGetProcedures() {
        assertTrue(promedService.getProcedures().stream().allMatch(name -> name.contains("Proc")));
    }

    @Test
    void should_getOnlMedicines_whenCallGetMedicines() {
        assertTrue(promedService.getMedicines().stream().allMatch(name -> name.contains("Med")));
    }

}