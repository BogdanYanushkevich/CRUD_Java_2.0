package com.bogdan_yanushkevich.javacore.crud;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SkillServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    private final Long id = 1L;
    private final String name = "sql";
    @Mock
    private Skill skill;

    @Mock
    private List<Skill> skills;
    @Mock
    private SkillServiceImpl skillService;

    @Test
    public void createTest() {
        doReturn(skill).when(skillService).create(name);
        assertEquals(skill, skillService.create(name));
    }

    @Test
    public void readTest() {
        doReturn(skill).when(skillService).read(id);
        assertEquals(skill, skillService.read(id));
    }

    @Test
    public void updateTest() {
        doReturn(skill).when(skillService).update(name, id);
        assertEquals(skill, skillService.update(name, id));
    }


    @Test
    public void getAllTest() {
        doReturn(skills).when(skillService).getALl();
        assertEquals(skills, skillService.getALl());
    }
}

