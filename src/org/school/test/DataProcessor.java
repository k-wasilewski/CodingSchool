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
            if (OperationUtil.isExitOperation(operation)) {
                break;
            }
            if (OperationUtil.isAddOperation(operation)) {
                add();
                showAll();
            } else if (OperationUtil.isEditOperation(operation)) {
                edit();
                showAll();
            } else if (OperationUtil.isDeleteOperation(operation)) {
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
