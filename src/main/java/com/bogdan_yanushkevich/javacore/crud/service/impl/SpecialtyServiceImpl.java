package com.bogdan_yanushkevich.javacore.crud.service.impl;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.repository.SpecialtyRepository;
import com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.JdbcSpecialtyRepositoryImpl;
import com.bogdan_yanushkevich.javacore.crud.service.SpecialtyService;
import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository sr;

    public SpecialtyServiceImpl() {
        this.sr = new JdbcSpecialtyRepositoryImpl();
    }

    public Specialty create(String name){
        return sr.create(name);
    }

    @Override
    public Specialty read(Long id){
        return sr.read(id);
    }

    @Override
    public Specialty update(String name, long id){
        return sr.update(name, id);
    }

    @Override
    public void delete(Long id){
        sr.delete(id);
    }

    @Override
    public List<Specialty> getALl() {
        return sr.getALl();
    }


}
