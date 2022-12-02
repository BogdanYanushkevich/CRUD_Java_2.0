package com.bogdan_yanushkevich.javacore.crud.model;

public class Skill extends BaseEntity<Long> {


    @Override
    public String toString() {
        return "Skill | " +
                "\tID: " + getId() + " \t| " +
                "Name: " + getName() + " \t| " +
                "Status: " + getStatus();
    }
}
