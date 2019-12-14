package org.school.test;

import java.util.Scanner;

class OperationUtil {

    public static String getLineFromUser(Scanner scanner, String header) {
        System.out.println(header);
        return scanner.nextLine();
    }

    public static int getIntFromUser(Scanner scanner, String header) {
        System.out.println(header);
        return Integer.parseInt(scanner.nextLine());
    }

    public static boolean isExitOperation(String operation) {
        return operation.equals("quit");
    }

    public static boolean isAddOperation(String operation) {
        return operation.equals("add");
    }

    public static boolean isEditOperation(String operation) {
        return operation.equals("edit");
    }

    public static boolean isDeleteOperation(String operation) {
        return operation.equals("delete");
    }

    public static boolean isViewOperation(String operation) {
        return operation.equals("view");
    }
}
