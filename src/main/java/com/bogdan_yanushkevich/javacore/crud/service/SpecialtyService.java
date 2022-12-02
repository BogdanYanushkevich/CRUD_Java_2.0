package com.bogdan_yanushkevich.javacore.crud.service;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

public interface SpecialtyService extends GenericService<Specialty, Long> {
    Specialty create(String name);

    Specialty update(String name, long id);
}
