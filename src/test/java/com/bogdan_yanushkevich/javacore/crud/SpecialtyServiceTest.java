package com.bogdan_yanushkevich.javacore.crud;


import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.repository.SpecialtyRepository;
import com.bogdan_yanushkevich.javacore.crud.service.SpecialtyService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SpecialtyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SpecialtyServiceTest {
    private final Long id = 2L;
    private final SpecialtyRepository specialtyRepository = Mockito.mock(SpecialtyRepository.class);
    private final SpecialtyService specialtyService = new SpecialtyServiceImpl(specialtyRepository);


    private Specialty getSpecialty() {
        return new Specialty();
    }

    private List<Specialty> getSpecialties() {
        List<Specialty> specialties = new ArrayList<>();
        specialties.add(getSpecialty());
        return specialties;
    }

    @Test
    public void createTest() {
        doReturn(getSpecialty()).when(specialtyRepository).create(any(Specialty.class));
        assertEquals(getSpecialty(), specialtyService.create(getSpecialty()));
    }

    @Test
    public void readTest() {
        doReturn(getSpecialty()).when(specialtyRepository).read(anyLong());
        assertEquals(getSpecialty(), specialtyService.read(id));
    }

    @Test
    public void updateTest() {
        doReturn(getSpecialty()).when(specialtyRepository).update(any(Specialty.class));
        assertEquals(getSpecialty(), specialtyService.update(getSpecialty()));
    }


    @Test
    public void getAllTest() {
        doReturn(getSpecialties()).when(specialtyRepository).getALl();
        assertEquals(getSpecialties(), specialtyService.getALl());
    }
}
