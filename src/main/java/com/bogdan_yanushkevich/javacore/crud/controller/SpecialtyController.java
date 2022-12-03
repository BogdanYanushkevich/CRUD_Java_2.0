package com.bogdan_yanushkevich.javacore.crud.controller;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.service.SpecialtyService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SpecialtyServiceImpl;

import java.util.List;

public class SpecialtyController {

    private final SpecialtyService ss;

    public SpecialtyController() {
        this.ss = new SpecialtyServiceImpl();
    }

    public Specialty create(String name) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        return ss.create(specialty);
    }

    public Specialty read(Long id) {

        return ss.read(id);
    }

    public Specialty update(String name, long id) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setId(id);
        specialty.setStatus(Status.ACTIVE);
        return ss.update(specialty);
    }


    public void delete(Long id) {

        ss.delete(id);
    }

    public List<Specialty> showAll() {
        return ss.getALl();
    }

}


