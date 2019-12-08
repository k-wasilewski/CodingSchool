package org.school.test;

import java.util.Scanner;

class Processor {
    public void run() {
        System.out.println("Nie zaimplementowano metody run");
    }

    protected static String getLineFromUser(Scanner scanner, String header) {
        System.out.println(header);
        return scanner.nextLine();
    }
}
