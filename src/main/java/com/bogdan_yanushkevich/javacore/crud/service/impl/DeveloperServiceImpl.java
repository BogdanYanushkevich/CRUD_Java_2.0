package com.bogdan_yanushkevich.javacore.crud.service.impl;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.repository.DeveloperRepository;
import com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.JdbcDeveloperRepositoryImpl;
import com.bogdan_yanushkevich.javacore.crud.service.DeveloperService;

import java.util.List;


public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository dr;

    public DeveloperServiceImpl() {
        this.dr = new JdbcDeveloperRepositoryImpl();
    }

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.dr = developerRepository;
    }

    @Override
    public Developer create(Developer developer) {
        return dr.create(developer);
    }

    @Override
    public Developer read(Long id) {
        return dr.read(id);
    }


    public Developer update(Developer developer) {
        return dr.update(developer);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Developer> getALl() {
        return dr.getALl();
    }
}
