package com.bogdan_yanushkevich.javacore.crud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Developer extends BaseEntity<Long> {


    private String lastName;
    List<Skill> skills = new ArrayList<>();
    private Specialty specialty;

    public Developer(Long Id, String firstName, String lastName, Specialty specialty, List<Skill>skills, Status status) {
        setId(id);
        setName(firstName);
        this.lastName = lastName;
        setSpecialty(specialty);
        addSkills(skills);
        setStatus(status);
    }
    public Developer() {

    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;
        Developer developer = (Developer) o;
        return getLastName().equals(developer.getLastName()) && getSkills().equals(developer.getSkills()) && getSpecialty().equals(developer.getSpecialty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getSkills(), getSpecialty());
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
