package org.school.test;

import org.school.Exercise;
import org.school.Solution;
import org.school.User;
import org.school.dao.ExerciseDao;
import org.school.dao.SolutionDao;
import org.school.dao.UserDao;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

class AddSolutionProcessor extends Processor {

    private int userId;
    private UserDao userDao;
    private SolutionDao solutionDao;
    private ExerciseDao exerciseDao;

    AddSolutionProcessor(int userId) {
        this.userId = userId;
        this.userDao = new UserDao();
        this.solutionDao = new SolutionDao();
        this.exerciseDao = new ExerciseDao();
    }

    @Override
    public void run() {
        User user = userDao.read(userId);
        System.out.println("Rozwiązanie użytkownika: " + user);
        while (true) {
            final String operation = getOperationFromUser();
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

    private String getOperationFromUser() {
        System.out.println();
        System.out.println("Wybierz jedną z opcji:: ");
        System.out.println("add - dodawanie rozwiązania");
        System.out.println("view - przeglądanie rozwiązań");
        System.out.println("quit - zamknęcie aplikacji");

        final Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    private void add() {

        Solution[] allUserSolutions = solutionDao.findAllByUserId(userId);
        Exercise[] unresolvedExercises = getUnresolvedExercises(allUserSolutions);
        show(unresolvedExercises);

        Scanner scanner = new Scanner(System.in);
        int exerciseId = OperationUtil.getIntFromUser(scanner, "Podaj id zadania, które chcesz rozwiązać:");
        if(canExerciseBeResolved(exerciseId, unresolvedExercises)) {

            String description = OperationUtil.getLineFromUser(scanner, "Podaj rozwiazanie zadania:");

            for (Solution userSolution : allUserSolutions) {
                if (userSolution.getExerciseId() == exerciseId) {
                    userSolution.setUpdated(new Date()); //current date
                    userSolution.setDescription(description);

                    solutionDao.update(userSolution);
                }
            }

        } else {
            System.out.println("Nie mozna zapisać rozwiązania dla zadania o id: " + exerciseId);
        }
    }

    private Exercise[] getUnresolvedExercises(Solution[] allUserSolutions) {

        Exercise[] unresolved = new Exercise[0];

        for (Solution solution : allUserSolutions) {
            if (isDescriptionEmpty(solution)) {

                Exercise exercise = exerciseDao.read(solution.getExerciseId());
                Exercise[] tmpExercises = Arrays.copyOf(unresolved, unresolved.length + 1);
                tmpExercises[unresolved.length] = exercise;
                unresolved = tmpExercises;
            }
        }

        return unresolved;
    }

    private void show(Exercise[] unresolvedExercises) {

        System.out.println("Użytkownika o id: " + userId + " nie rozwiązał następujących zadań:");
        for (Exercise unresolvedExercise : unresolvedExercises) {
            System.out.println(unresolvedExercise);
        }
    }

    private boolean canExerciseBeResolved(int exerciseId, Exercise[] unresolvedExercises) {
        for (Exercise unresolvedExercise : unresolvedExercises) {
            if(unresolvedExercise.getId() == exerciseId) {
                return true;
            }
        }

        return false;
    }

    private boolean isDescriptionEmpty(Solution userSolutions) {
        return userSolutions.getDescription() == null || userSolutions.getDescription().isBlank();
    }

    private void view() {
        Solution[] allUserSolutions = solutionDao.findAllByUserId(userId);
        System.out.println("Rozwiazania użytkownika:");
        for (Solution solution : allUserSolutions) {
            System.out.println(solution);
        }
    }
}
