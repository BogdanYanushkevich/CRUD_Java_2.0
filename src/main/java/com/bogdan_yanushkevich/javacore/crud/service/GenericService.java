package com.bogdan_yanushkevich.javacore.crud.service;
import java.util.List;

public interface GenericService <T, ID>{

    T read(Long ID);

    void delete(ID id);

    List<T> getALl();
}
