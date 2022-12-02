package com.bogdan_yanushkevich.javacore.crud;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SpecialtyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SpecialtyServiceTest {
    private final Long id = 1L;
    private final String name = "Java";
    @Mock
    private Specialty specialty;

    @Mock
    private List<Specialty> specialties;
    @Mock
    private SpecialtyServiceImpl specialtyService;

    @Test
    public void createTest() {
        doReturn(specialty).when(specialtyService).create(name);
        assertEquals(specialty, specialtyService.create(name));
    }

    @Test
    public void readTest() {
        doReturn(specialty).when(specialtyService).read(id);
        assertEquals(specialty, specialtyService.read(id));
    }

    @Test
    public void updateTest() {
        doReturn(specialty).when(specialtyService).update(name, id);
        assertEquals(specialty, specialtyService.update(name, id));
    }


    @Test
    public void getAllTest() {
        doReturn(specialties).when(specialtyService).getALl();
        assertEquals(specialties, specialtyService.getALl());
    }
}

