package com.bogdan_yanushkevich.javacore.crud.service;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

import java.util.List;

public interface DeveloperService extends GenericService<Developer, Long> {
    Developer create(String firstname, String lastname, List<Skill> skills, Specialty specialty);

    Developer update(Long id, String firstname, String lastname, List<Skill> skills, Specialty specialty);
}
