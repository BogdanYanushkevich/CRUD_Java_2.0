package com.bogdan_yanushkevich.javacore.crud;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;


import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.DeveloperRepository;
import com.bogdan_yanushkevich.javacore.crud.service.impl.DeveloperServiceImpl;
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
public class DeveloperServiceTest {

    private final Long id = 2L;
    private final DeveloperRepository developerRepository = Mockito.mock(DeveloperRepository.class);
    private final DeveloperServiceImpl developerService = new DeveloperServiceImpl(developerRepository);
    private final Specialty specialty = new Specialty();
    private final List<Skill> skills = new ArrayList<>();
    private final Status status = Status.ACTIVE;


    private Developer getDeveloper() {
        return new Developer(id, "Jan", "Klod", specialty, skills, status);
    }

    private List<Developer> getDevelopers() {
        List<Developer> developers = new ArrayList<>();
        developers.add(getDeveloper());
        return developers;
    }

    @Test
    public void createTest() {
        doReturn(getDeveloper()).when(developerRepository).create(any(Developer.class));
        assertEquals(getDeveloper(), developerService.create(getDeveloper()));
    }

    @Test
    public void readTest() {
        doReturn(getDeveloper()).when(developerRepository).read(anyLong());
        assertEquals(getDeveloper(), developerService.read(id));
    }

    @Test
    public void updateTest() {
        doReturn(getDeveloper()).when(developerRepository).update(any(Developer.class));
        assertEquals(getDeveloper(), developerService.update(getDeveloper()));
    }


    @Test
    public void getAllTest() {
        doReturn(getDevelopers()).when(developerRepository).getALl();
        assertEquals(getDevelopers(), developerService.getALl());
    }
}