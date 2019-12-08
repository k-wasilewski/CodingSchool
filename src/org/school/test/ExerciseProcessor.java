package org.school.test;

import org.school.Exercise;
import org.school.dao.ExerciseDao;

import java.util.Scanner;

class ExerciseProcessor extends DataProcessor {
    ExerciseDao exerciseDao;

    public ExerciseProcessor() {
        exerciseDao = new ExerciseDao();
    }

    @Override
    protected void showAll() {
        Exercise[] all = exerciseDao.findAll();

        System.out.println("Wszystkie zadania:");
        for (Exercise exercise : all) {
            System.out.println(exercise);
        }
    }

    @Override
    protected void add() {
        Scanner scanner = new Scanner(System.in);
        String title = getLineFromUser(scanner, "Tytuł zadania");
        String description = getLineFromUser(scanner, "Opis zadania");

        Exercise exercise = new Exercise(title, description);
        exerciseDao.create(exercise);
    }

    @Override
    protected void edit() {
        Scanner scanner = new Scanner(System.in);
        String id = getLineFromUser(scanner, "Id zadania do edycji");
        String title = getLineFromUser(scanner, "Tytuł zadania");
        String description = getLineFromUser(scanner, "Opis zadania");

        Exercise exercise = new Exercise(title, description);
        exercise.setId(Integer.parseInt(id));
        exerciseDao.update(exercise);
    }

    @Override
    protected void delete() {
        Scanner scanner = new Scanner(System.in);
        String id = getLineFromUser(scanner, "Id zadania do usunięcia");

        exerciseDao.delete(Integer.parseInt(id));
    }
}
