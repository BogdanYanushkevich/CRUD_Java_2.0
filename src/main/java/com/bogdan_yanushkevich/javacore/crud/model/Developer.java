package com.bogdan_yanushkevich.javacore.crud.model;

import java.util.ArrayList;
import java.util.List;

public class Developer extends BaseEntity<Long> {


    private String lastName;
    List<Skill> skills = new ArrayList<>();
    private Specialty specialty;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {

        return skills;
    }


    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addSkills(List<Skill> nSkills) {

        this.skills = nSkills;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }


    @Override
    public String toString() {
        return "Developer | " +
                "\tID: " + getId() + " \t| " +
                "\tName: " + getName() + " \t| " +
                "\tLastName: " + getLastName() + " \t| " +
                "\tSkills: " + getSkills() + " \t| " +
                "\tSpecialty: " + getSpecialty() + " \t| " +
                "\tStatus: " + getStatus();
    }
}
