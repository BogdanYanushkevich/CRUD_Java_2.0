package com.bogdan_yanushkevich.javacore.crud.view;


import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeneralView<T> {


    private final Scanner sc = new Scanner(System.in);


    public void menuTitle() {
        System.out.println("""
                                
                Make your choice:
                                
                1. Create.
                2. Read.
                3. Update.
                4. Delete.
                5. Show all variants.
                0. Exit.
                                
                """);

    }

    public void makeChoice() throws SQLException {

        switch (checkChoice(5, 0)) {
            case 1, 2, 3, 4, 5, 0 -> {

            }
        }
    }

    public int checkChoice(int caseSize, int minCase) {


        int choice;
        Scanner sc = new Scanner(System.in);
        try {
            choice = Integer.parseInt(sc.nextLine());
            if (choice > caseSize || choice < minCase) {
                System.out.println("Invalid option. Try again.\n");
                checkChoice(caseSize, minCase);
            }
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Repeat entry");
            checkChoice(caseSize, minCase);
        }
        return -1;
    }

    protected Long checkCorrect() {
        long field;
        while (true) {
            try {
                field = Long.parseLong(sc.nextLine());
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Repeat entry");
                checkCorrect();
            }
        }
        return field;
    }


    protected boolean checkForNull(T entity) {
        return entity == null;
    }


    protected String addLine() {

        return sc.nextLine();
    }

    protected void print(List<T> entity) {
        Iterator<T> itr = entity.listIterator();
        itr.forEachRemaining(System.out::println);
    }

    protected void print(T entity) {
        System.out.println(entity);
    }

    protected void print(String message, T entity) {
        System.out.println(message + entity);
    }

    protected void print(String message) {
        System.out.println(message);
    }

}