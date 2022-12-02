package com.bogdan_yanushkevich.javacore.crud.model;

public class Specialty extends BaseEntity<Long> {


    @Override
    public String toString() {
        return "Specialty | " +
                "\tID: " + getId() + " \t| " +
                "Name: " + getName() + " \t| " +
                "Status: " + getStatus();
    }
}
