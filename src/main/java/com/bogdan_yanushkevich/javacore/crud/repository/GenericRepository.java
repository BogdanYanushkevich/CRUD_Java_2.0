package com.bogdan_yanushkevich.javacore.crud.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {


    T read(Long ID);

    boolean delete(ID id);

    List<T> getALl();

}
