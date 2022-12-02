package com.bogdan_yanushkevich.javacore.crud.service.impl;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.repository.SkillRepository;
import com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.JdbcSkillRepositoryImpl;
import com.bogdan_yanushkevich.javacore.crud.service.SkillService;
import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository sr;

    public SkillServiceImpl() {
        this.sr = new JdbcSkillRepositoryImpl();
    }

    public Skill create(String name){
        return sr.create(name);
    }

    @Override
    public Skill read(Long id){
        return sr.read(id);
    }

    @Override
    public Skill update(String name, long id){
        return sr.update(name, id);
    }

    @Override
    public void delete(Long id){
        sr.delete(id);
    }

    @Override
    public List<Skill> getALl() {
        return sr.getALl();
    }
}
