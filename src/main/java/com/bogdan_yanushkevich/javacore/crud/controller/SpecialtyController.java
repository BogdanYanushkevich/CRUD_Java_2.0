package com.bogdan_yanushkevich.javacore.crud.controller;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.service.SpecialtyService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SpecialtyServiceImpl;
import java.util.List;

public class SpecialtyController {

    private final SpecialtyService ss;

    public SpecialtyController() {
        this.ss = new SpecialtyServiceImpl();
    }
    public Specialty create(String name){
        return ss.create(name);
    }

    public Specialty read(Long id){

        return ss.read(id);
    }

    public Specialty update(String name, long id){
        return ss.update(name, id);
    }


    public void delete(Long id){

        ss.delete(id);
    }

    public List<Specialty> showAll() {
        return ss.getALl();
    }

}


