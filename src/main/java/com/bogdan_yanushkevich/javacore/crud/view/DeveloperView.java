package com.bogdan_yanushkevich.javacore.crud.view;

import com.bogdan_yanushkevich.javacore.crud.controller.DeveloperController;
import com.bogdan_yanushkevich.javacore.crud.controller.SkillController;
import com.bogdan_yanushkevich.javacore.crud.controller.SpecialtyController;
import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class DeveloperView extends GeneralView<Developer> {

    private Long id;

    DeveloperController dc = new DeveloperController();


    @Override
    public void makeChoice() throws SQLException {
        menuTitle();
        switch (checkChoice(5, 0)) {
            case 1 -> {
                Developer dev = new Developer();
                print("Enter developer firstname: ");
                dev.setName(addLine());
                print("Enter developer lastname: ");
                dev.setLastName(addLine());
                Skill newSkill = skillReturner();
                dev.addSkill(newSkill);
                print("Success added.");
                dev.setSpecialty(specialtyReturner());
                print("Success added.");
                print("You added: ", dc.create(dev.getName(), dev.getLastName(), dev.getSkills(), dev.getSpecialty()));
                makeChoice();
            }

            case 2 -> {
                print("Enter ID: ");
                id = checkCorrect();
                if (checkForNull(dc.read(id))) {
                    print("Incorrect element ID, please try again.");
                    makeChoice();
                } else {
                    print(dc.read(id));
                    makeChoice();
                }

            }
            case 3 -> {
                print("Enter the ID of the element you want to change: ");
                id = checkCorrect();
                Developer dev = dc.read(id);
                if (checkForNull(dev)) {
                    print("Incorrect element ID, please try again.");
                    makeChoice();
                } else {
                    print(dev);
                    dev = updateMenu(dev);
                    print("You changed: ");
                    print(dc.update(dev.getId(), dev.getName(), dev.getLastName(), dev.getSkills(), dev.getSpecialty()));
                    makeChoice();
                }

            }
            case 4 -> {

                print("Enter the ID of the element you want to delete: ");
                id = checkCorrect();

                if (!checkForNull(dc.read(id))) {
                    dc.delete(id);
                    print("You deleted: ", dc.read(id));
                    makeChoice();

                } else {
                    print("Incorrect element ID, please try again.");
                    makeChoice();
                }
            }
            case 5 -> {
                List<Developer> developers = dc.showAll();
                if (developers == null) {
                    print("List is empty.");
                } else {
                    print(developers);
                }
                makeChoice();
            }
            case 0 -> {
                ConsoleRunner cr = new ConsoleRunner();
                cr.run();
            }
        }

    }


    public Skill skillReturner() {
        print("Skill Selection.");
        subCreateMenu();
        SkillController sc = new SkillController();


        switch (checkChoice(5, 0)) {
            case 1 -> {
                (sc.showAll()).forEach(System.out::println);
                print("Select skill enter ID:");
                id = checkCorrect();
                Skill skill;
                skill = sc.read(id);
                if (skill == null) {
                    print("There is no such skill, add a new one.");
                    skillReturner();
                } else {
                    System.out.println("skill found");
                    return skill;
                }
            }
            case 2 -> {

                print("Enter skill name: ");
                Skill skill;
                skill = sc.create(addLine());
                if (skill != null) {
                    return skill;
                } else {
                    print("The object already exists, please try again.");
                    skillReturner();
                }


            }
        }
        return null;
    }

    public Specialty specialtyReturner() throws SQLException {
        print("Specialty Selection.");
        subCreateMenu();
        SpecialtyController spc = new SpecialtyController();


        switch (checkChoice(2, 1)) {
            case 1 -> {
                (spc.showAll()).forEach(System.out::println);
                print("Select skill enter ID:");
                id = checkCorrect();
                Specialty specialty = spc.read(id);
                if (specialty == null) {
                    print("There is no such skill, add a new one.");
                    specialtyReturner();
                } else {
                    return specialty;
                }
            }
            case 2 -> {
                print("Enter specialty name: ");
                Specialty specialty = spc.create(addLine());
                if (specialty != null) {
                    return specialty;
                } else {
                    print("The object already exists, please try again.");
                    specialtyReturner();
                }
            }
        }
        return null;
    }

    public Developer updateMenu(Developer dev) {
        subUpdateMenu();
        switch (checkChoice(4, 0)) {
            case 1 -> {
                print("Enter developer firstname: ");
                dev.setName(addLine());
                print("Successful changed\n", dev);
                updateMenu(dev);
            }
            case 2 -> {
                print("Enter developer lastname: ");
                dev.setLastName(addLine());
                print("Successful changed\n", dev);
                updateMenu(dev);
            }
            case 3 -> {
                dev = updateSkillMenu(dev);
                print("Successful changed\n", dev);
                updateMenu(dev);
            }
            case 4 -> {
                try {
                    dev.setSpecialty(specialtyReturner());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                print("Successful changed\n", dev);
                updateMenu(dev);
            }
            case 0 -> {
                return dev;
            }
        }
        return dev;
    }

    public Developer updateSkillMenu(Developer dev) {
        subUpdateSkillsMenu();
        switch (checkChoice(2, 0)) {
            case 1 -> {
                Iterator<Skill> itr = dev.getSkills().listIterator();
                itr.forEachRemaining(System.out::println);
                print("Enter ID of element you want to delete: ");
                id = checkCorrect();
                dev.getSkills().removeIf(s -> s.getId().equals(id));
            }

            case 2 -> dev.addSkill(skillReturner());
            case 0 -> updateMenu(dev);

        }
        return dev;
    }

    public void subCreateMenu() {
        System.out.println("""
                                
                Make your choice:
                                
                1. Choose from existing.
                2. Create new.
                               
                                
                """);
    }

    public void subUpdateSkillsMenu() {
        System.out.println("""
                                
                Make your choice:
                                
                1. Delete.
                2. Add new.
                                 
                0. Exit.
                                
                """);
    }

    public void subUpdateMenu() {
        System.out.println("""
                                
                You want to change:
                                
                1. First name.
                2. Last name.
                3. SKills.
                4. Specialty.
                                
                0. Save/Exit.
                            
                            
                """);
    }
}
