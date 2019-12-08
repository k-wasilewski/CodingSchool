package org.school.test;

import org.school.Exercise;
import org.school.Solution;
import org.school.User;
import org.school.dao.ExerciseDao;
import org.school.dao.SolutionDao;
import org.school.dao.UserDao;

import java.util.Date;
import java.util.Scanner;

class SolutionProcessor extends Processor {

    UserDao userDao;
    ExerciseDao exerciseDao;
    SolutionDao solutionDao;

    public SolutionProcessor() {
        this.userDao = new UserDao();
        this.exerciseDao = new ExerciseDao();
        this.solutionDao = new SolutionDao();
    }

    @Override
    public void run() {
        while (true) {
            String operation = getOperationFromUser();
            if (OperationUtil.isExitOperation(operation)) {
                break;
            }

            if (OperationUtil.isAddOperation(operation)) {
                add();
            } else if (OperationUtil.isViewOperation(operation)) {
                view();
            } else {
                System.out.println("Nieznana komenda");
            }
        }
    }

    protected String getOperationFromUser() {
        System.out.println();
        System.out.println("Wybierz jedną z opcji:: ");
        System.out.println("add - przypisywanie zadań do użytkowników");
        System.out.println("view - przeglądanie rozwiązań danego użytkownika");
        System.out.println("quit - zamknęcie aplikacji");

        final Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    protected void add() {

        User[] users = userDao.findAll();

        System.out.println("Wszyscy użytkownicy:");
        for (User user : users) {
            System.out.println(user);
        }

        Scanner scanner = new Scanner(System.in);
        int userId = OperationUtil.getIntFromUser(scanner, "Id użytkownika dla którego dodać zadanie");

        Exercise[] all = exerciseDao.findAll();

        System.out.println("Wszystkie zadania:");
        for (Exercise exercise : all) {
            System.out.println(exercise);
        }

        int exerciseId = OperationUtil.getIntFromUser(scanner, "Id zadania dla wybranego użytkownika");

        Solution solution = new Solution(new Date(), null, "", userId, exerciseId);
        solutionDao.create(solution);
    }

    protected void view() {
        Scanner scanner = new Scanner(System.in);
        int userId = OperationUtil.getIntFromUser(scanner, "rozwiązania użytkownika o id:");

        Solution[] userSolutions = solutionDao.findAllByUserId(userId);
        System.out.println("Rozwiązania użytkownika:");
        for (Solution solution : userSolutions) {
            System.out.println(solution);
        }
    }
}
