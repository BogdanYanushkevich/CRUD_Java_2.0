package com.bogdan_yanushkevich.javacore.crud.view;


import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleRunner {

    private final DeveloperView dv = new DeveloperView();
    private final SkillView skillView = new SkillView();
    private final SpecialtyView specView = new SpecialtyView();


    private static String menuTitle() {
        return """
                Make your choice.
                1. Developers.
                2. Skills.
                3. Specialties.
                0. Exit.
                """;
    }


    public void run() {

        System.out.print(menuTitle());
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            if (choice > 3 || choice < 0) {
                System.out.println("Invalid option. Try again.");
                run();
            }
            switch (choice) {
                case 1 -> dv.makeChoice();

                case 2 -> skillView.makeChoice();

                case 3 -> specView.makeChoice();
                case 0 -> {
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Repeat entry");
            run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
