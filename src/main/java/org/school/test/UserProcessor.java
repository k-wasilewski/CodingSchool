package org.school.test;

import org.school.User;
import org.school.dao.UserDao;

import java.util.Scanner;

class UserProcessor extends DataProcessor {
    UserDao userDao;

    public UserProcessor() {
        this.userDao = new UserDao();
    }

    @Override
    protected void showAll() {
        User[] users = userDao.findAll();

        System.out.println("Wszyscy użytkownicy:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    protected void add() {
        Scanner scanner = new Scanner(System.in);
        String userName = OperationUtil.getLineFromUser(scanner, "Imię użytkownika");
        String email = OperationUtil.getLineFromUser(scanner, "Email użytkownika");
        String password = OperationUtil.getLineFromUser(scanner, "Hasło użytkownika");

        User user = new User(userName, email, password);

        userDao.create(user);
    }

    @Override
    protected void edit() {
        Scanner scanner = new Scanner(System.in);
        int id = OperationUtil.getIntFromUser(scanner, "Id użytkownika do edycji");
        String userName = OperationUtil.getLineFromUser(scanner, "Imię użytkownika");
        String email = OperationUtil.getLineFromUser(scanner, "Email użytkownika");
        String password = OperationUtil.getLineFromUser(scanner, "Hasło użytkownika");

        User user = new User(userName, email, password);
        user.setId(id);

        userDao.update(user);
    }

    @Override
    protected void delete() {
        Scanner scanner = new Scanner(System.in);
        int id = OperationUtil.getIntFromUser(scanner, "Id użytkownika do usunięcia");
        userDao.delete(id);
    }
}
