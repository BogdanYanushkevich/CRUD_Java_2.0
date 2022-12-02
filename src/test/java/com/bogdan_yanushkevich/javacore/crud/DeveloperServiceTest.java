package com.bogdan_yanushkevich.javacore.crud;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

import com.bogdan_yanushkevich.javacore.crud.service.impl.DeveloperServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {

    private final Long id = 1L;
    private final String firstName = "Vasya";
    private final String lastName = "Lojkin";
    @Mock
    private Developer developer;
    @Mock
    private Specialty specialty;
    @Mock
    private List<Developer> developers;
    @Mock
    private List<Skill> skills;
    @Mock
    private DeveloperServiceImpl developerService;

    @Test
    public void createTest() {
        doReturn(developer).when(developerService).create(firstName, lastName, skills, specialty);
        assertEquals(developer, developerService.create(firstName, lastName, skills, specialty));
    }

    @Test
    public void readTest() {
        doReturn(developer).when(developerService).read(id);
        assertEquals(developer, developerService.read(id));
    }

    @Test
    public void updateTest() {
        doReturn(developer).when(developerService).update(id, firstName, lastName, skills, specialty);
        assertEquals(developer, developerService.update(id, firstName, lastName, skills, specialty));
    }


    @Test
    public void getAllTest() {
        doReturn(developers).when(developerService).getALl();
        assertEquals(developers, developerService.getALl());
    }
}