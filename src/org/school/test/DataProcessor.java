package org.school.test;

import java.util.Scanner;

/**
 * Klasa zawiera logikę pobierania operacji od użytkownika.
 * Koknretne działania znajdują się w klasach dziedziczących po DataManager
 */
class DataProcessor extends Processor {

    @Override
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

    protected String getOperationFromUser() {
        System.out.println();
        System.out.println("Wybierz jedną z opcji:: ");
        System.out.println("add - dodawanie");
        System.out.println("edit - edycja");
        System.out.println("delete - usuwanie");
        System.out.println("quit - zamknęcie aplikacji");

        final Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    protected boolean isExitOperation(String operation) {
        return operation.equals("quit");
    }

    protected boolean isAddOperation(String operation) {
        return operation.equals("add");
    }

    protected boolean isEditOperation(String operation) {
        return operation.equals("edit");
    }

    protected boolean isDeleteOperation(String operation) {
        return operation.equals("delete");
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
