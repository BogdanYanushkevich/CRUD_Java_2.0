package com.bogdan_yanushkevich.javacore.crud.controller;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.service.SkillService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SkillServiceImpl;
import java.util.List;


public class SkillController {

    private final SkillService ss;

    public SkillController() {
        this.ss = new SkillServiceImpl();
    }


    public Skill create(String name){
        return ss.create(name);
    }

    public Skill read(Long id){

        return ss.read(id);
    }

    public Skill update(String name, long id){
        return ss.update(name, id);
    }


    public void delete(Long id){

        ss.delete(id);
    }

    public List<Skill> showAll() {
        return ss.getALl();
    }

}

