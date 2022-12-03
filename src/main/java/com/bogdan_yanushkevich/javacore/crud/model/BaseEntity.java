package com.bogdan_yanushkevich.javacore.crud.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(getId(), that.getId()) && getStatus() == that.getStatus() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getName());
    }
}