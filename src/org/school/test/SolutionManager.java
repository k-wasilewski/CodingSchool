package org.school.test;

import org.school.Exercise;
import org.school.Solution;
import org.school.User;
import org.school.dao.ExerciseDao;
import org.school.dao.SolutionDao;
import org.school.dao.UserDao;

import java.util.Date;
import java.util.Scanner;

class SolutionManager extends DataManager {

    UserDao userDao;
    ExerciseDao exerciseDao;
    SolutionDao solutionDao;

    public SolutionManager() {
        this.userDao = new UserDao();
        this.exerciseDao = new ExerciseDao();
        this.solutionDao = new SolutionDao();
    }

    @Override
    public void run() {
        while (true) {
            final String operation = getOperationFromUser();
            if (isExitOperation(operation)) {
                break;
            }
            if (isAddOperation(operation)) {
                add();
            } else if (isViewOperation(operation)) {
                view();
            } else {
                System.out.println("Nieznana komenda");
            }
        }
    }

    @Override
    protected String getOperationFromUser() {
        System.out.println();
        System.out.println("Wybierz jedną z opcji:: ");
        System.out.println("add - przypisywanie zadań do użytkowników");
        System.out.println("view - przeglądanie rozwiązań danego użytkownika");
        System.out.println("quit - zamknęcie aplikacji");

        final Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    private boolean isViewOperation(String operation) {
        return operation.equals("view");
    }

    @Override
    protected void add() {

        User[] users = userDao.findAll();

        System.out.println("Wszyscy użytkownicy:");
        for (User user : users) {
            System.out.println(user);
        }

        Scanner scanner = new Scanner(System.in);
        String userId = getLineFromUser(scanner, "Id użytkownika dla którego dodać zadanie");

        Exercise[] all = exerciseDao.findAll();

        System.out.println("Wszystkie zadania:");
        for (Exercise exercise : all) {
            System.out.println(exercise);
        }

        String exerciseId = getLineFromUser(scanner, "Id zadania dla wybranego użytkownika");

        Solution solution = new Solution(new Date(), null, "", Integer.parseInt(userId), Integer.parseInt(exerciseId));
        solutionDao.create(solution);
    }

    protected void view() {
        Scanner scanner = new Scanner(System.in);
        String userId = getLineFromUser(scanner, "rozwiązania użytkownika o id:");

        Solution[] userSolutions = solutionDao.findAllByUserId(Integer.parseInt(userId));
        System.out.println("Rozwiązania użytkownika:");
        for (Solution solution : userSolutions) {
            System.out.println(solution);
        }
    }
}
