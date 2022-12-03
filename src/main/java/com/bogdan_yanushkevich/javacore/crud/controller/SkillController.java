package com.bogdan_yanushkevich.javacore.crud.controller;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.service.SkillService;
import com.bogdan_yanushkevich.javacore.crud.service.impl.SkillServiceImpl;

import java.util.List;


public class SkillController {

    private final SkillService ss;

    public SkillController() {
        this.ss = new SkillServiceImpl();
    }


    public Skill create(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return ss.create(skill);
    }

    public Skill read(Long id) {

        return ss.read(id);
    }

    public Skill update(String name, long id) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setId(id);
        skill.setStatus(Status.ACTIVE);
        return ss.update(skill);
    }


    public void delete(Long id) {

        ss.delete(id);
    }

    public List<Skill> showAll() {
        return ss.getALl();
    }

}

