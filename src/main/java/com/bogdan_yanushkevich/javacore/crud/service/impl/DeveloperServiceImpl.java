package com.bogdan_yanushkevich.javacore.crud.service.impl;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.repository.DeveloperRepository;
import com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.JdbcDeveloperRepositoryImpl;
import com.bogdan_yanushkevich.javacore.crud.service.DeveloperService;
import java.util.List;


public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository dr;

    public DeveloperServiceImpl() {
        this.dr = new JdbcDeveloperRepositoryImpl();
    }

    @Override
    public Developer create(String firstname, String lastname, List<Skill> skills, Specialty specialty) {
        return dr.create(firstname, lastname, skills, specialty);
    }

    @Override
    public Developer read(Long id) {
        return dr.read(id);
    }


    public Developer update(Long id, String firstname, String lastname, List<Skill> skills, Specialty specialty) {
        return dr.update(id, firstname, lastname, skills, specialty);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Developer> getALl() {
        return dr.getALl();
    }
}
