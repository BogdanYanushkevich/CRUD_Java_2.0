package com.bogdan_yanushkevich.javacore.crud.repository;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    public Developer create(Developer developer);

    public Developer update(Developer developer);
}

