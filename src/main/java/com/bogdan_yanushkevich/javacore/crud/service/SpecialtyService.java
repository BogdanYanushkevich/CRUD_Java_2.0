package com.bogdan_yanushkevich.javacore.crud.service;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

public interface SpecialtyService extends GenericService<Specialty, Long> {
    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);
}
