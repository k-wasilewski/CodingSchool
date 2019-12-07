package org.school.test;

import java.util.Scanner;

/**
 * Klasa zawiera logikę pobierania operacji od użytkownika.
 * Koknretne działania znajdują się w klasach dziedziczących po DataManager
 */
class DataManager {

    public void run() {

        showAll();

        while (true) {
            final String operation = getOperationFromUser();
            if (isExitOperation(operation)) {
                break;
            }
            if (isAddOperation(operation)) {
                add();
                showAll();
            } else if (isEditOperation(operation)) {
                edit();
                showAll();
            } else if (isDeleteOperation(operation)) {
                delete();
                showAll();
            } else {
                System.out.println("Nieznana komenda");
            }
        }
    }

    private String getOperationFromUser() {
        System.out.println();
        System.out.println("Podaj kod operacji: ");
        System.out.println("add - dodawanie");
        System.out.println("edit - edycja");
        System.out.println("delete - usuwanie");
        System.out.println("quit - zamknęcie aplikacji");

        final Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    private boolean isExitOperation(String operation) {
        return operation.equals("quit");
    }

    private boolean isAddOperation(String operation) {
        return operation.equals("add");
    }

    private boolean isEditOperation(String operation) {
        return operation.equals("edit");
    }

    private boolean isDeleteOperation(String operation) {
        return operation.equals("delete");
    }


    protected static String getLineFromUser(Scanner scanner, String header) {
        System.out.println(header);
        return scanner.nextLine();
    }

    protected void showAll() {
        System.out.println("Nie zaimplementowano showAll");
    }

    protected void add() {
        System.out.println("Nie zaimplementowano add");
    }

    protected void edit() {

        System.out.println("Nie zaimplementowano edit");
    }

    protected void delete() {
        System.out.println("Nie zaimplementowano delete");
    }
}
