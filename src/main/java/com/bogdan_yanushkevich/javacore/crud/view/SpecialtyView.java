package com.bogdan_yanushkevich.javacore.crud.view;

import com.bogdan_yanushkevich.javacore.crud.controller.SpecialtyController;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;

import java.util.List;

public class SpecialtyView extends GeneralView<Specialty> {

    SpecialtyController sc = new SpecialtyController();


    @Override
    public void makeChoice() {
        menuTitle();

        Long id;
        switch (checkChoice(5, 0)) {
            case 1 -> {
                print("Enter the name: ");
                Specialty specialty;
                specialty = sc.create(addLine());
                if (!checkForNull(specialty)) {
                    print("You added: ", specialty);
                    makeChoice();
                } else {
                    print("The object already exists, please try again.");
                    makeChoice();
                }

            }
            case 2 -> {
                print("Enter ID: ");
                id = checkCorrect();

                if (checkForNull(sc.read(id))) {
                    print("Incorrect element ID, please try again.");
                    makeChoice();
                } else {
                    print(sc.read(id));
                    makeChoice();
                }

            }
            case 3 -> {


                print("Enter the ID of the element you want to change: ");
                id = checkCorrect();

                if (checkForNull(sc.read(id))) {
                    print("Incorrect element ID, please try again.");
                    makeChoice();

                } else {
                    print("Please enter new name: ");
                    print("You changed: ", sc.update(addLine(), id));
                    makeChoice();
                }

            }
            case 4 -> {

                print("Enter the ID of the element you want to delete: ");
                id = checkCorrect();
                if (!checkForNull(sc.read(id))) {
                    sc.delete(id);
                    print("You deleted: ", sc.read(id));
                    makeChoice();

                } else {
                    print("Incorrect element ID, please try again.");
                    makeChoice();
                }
            }
            case 5 -> {
                List<Specialty> specialty = sc.showAll();
                print(specialty);
                makeChoice();
            }
            case 0 -> {
                ConsoleRunner cr = new ConsoleRunner();
                cr.run();
            }
        }
    }
}
