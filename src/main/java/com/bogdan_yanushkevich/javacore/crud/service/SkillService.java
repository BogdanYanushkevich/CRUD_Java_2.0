package com.bogdan_yanushkevich.javacore.crud.service;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;

public interface SkillService extends GenericService<Skill, Long> {
    Skill create(Skill skill);

    Skill update(Skill skill);
}
