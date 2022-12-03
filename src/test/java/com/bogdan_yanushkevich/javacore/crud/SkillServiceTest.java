package com.bogdan_yanushkevich.javacore.crud;


import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.repository.SkillRepository;
import com.bogdan_yanushkevich.javacore.crud.service.SkillService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SkillServiceImpl;
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
public class SkillServiceTest {
    private final Long id = 2L;
    private final SkillRepository skillRepository = Mockito.mock(SkillRepository.class);
    private final SkillService skillService = new SkillServiceImpl(skillRepository);


    private Skill getSkill() {
        return new Skill();
    }

    private List<Skill> getSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(getSkill());
        return skills;
    }

    @Test
    public void createTest() {
        doReturn(getSkill()).when(skillRepository).create(any(Skill.class));
        assertEquals(getSkill(), skillService.create(getSkill()));
    }

    @Test
    public void readTest() {
        doReturn(getSkill()).when(skillRepository).read(anyLong());
        assertEquals(getSkill(), skillService.read(id));
    }

    @Test
    public void updateTest() {
        doReturn(getSkill()).when(skillRepository).update(any(Skill.class));
        assertEquals(getSkill(), skillService.update(getSkill()));
    }


    @Test
    public void getAllTest() {
        doReturn(getSkills()).when(skillRepository).getALl();
        assertEquals(getSkills(), skillService.getALl());
    }
}