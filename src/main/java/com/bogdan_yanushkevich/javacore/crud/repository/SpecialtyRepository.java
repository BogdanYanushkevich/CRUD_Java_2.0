package com.bogdan_yanushkevich.javacore.crud.repository;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

import java.sql.SQLException;

public interface SpecialtyRepository extends GenericRepository<Specialty, Long> {
    public Specialty create(String name);
    public Specialty update(String name, long id);
}
