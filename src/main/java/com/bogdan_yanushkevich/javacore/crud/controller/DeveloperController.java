package com.bogdan_yanushkevich.javacore.crud.controller;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.service.DeveloperService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.DeveloperServiceImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperService ds;

    public DeveloperController() {
        this.ds = new DeveloperServiceImpl();
    }


    public Developer create(String firstname, String lastname, List<Skill> skills, Specialty specialty) {
        return ds.create(firstname, lastname, skills, specialty);
    }

    public Developer read(Long field){
        return ds.read(field);
    }

    public Developer update(Long id, String firstname, String lastname, List<Skill> skills, Specialty specialty) {
        return ds.update(id, firstname, lastname, skills, specialty);
    }


    public void delete(Long id){
        ds.delete(id);
    }

    public List<Developer> showAll() {
        return ds.getALl();
    }

}
