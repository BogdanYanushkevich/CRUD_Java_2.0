package com.bogdan_yanushkevich.javacore.crud.model;

public class BaseEntity<T> {


    T id;
    private Status status;

    private String name;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}