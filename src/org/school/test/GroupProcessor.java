package org.school.test;

import org.school.Group;
import org.school.dao.GroupDao;

import java.util.Scanner;

class GroupProcessor extends DataProcessor {
    GroupDao groupDao;

    public GroupProcessor() {
        groupDao = new GroupDao();
    }

    @Override
    protected void showAll() {
        Group[] all = groupDao.findAll();

        System.out.println("Wszystkie grupy:");
        for (Group group : all) {
            System.out.println(group);
        }
    }

    @Override
    protected void add() {
        Scanner scanner = new Scanner(System.in);
        String name = OperationUtil.getLineFromUser(scanner, "Nazwa grupy:");

        Group group = new Group(name);
        groupDao.create(group);
    }

    @Override
    protected void edit() {
        Scanner scanner = new Scanner(System.in);
        String id = OperationUtil.getLineFromUser(scanner, "Id grupy do modyfikacji:");
        String name = OperationUtil.getLineFromUser(scanner, "Nazwa grupy:");

        Group group = new Group(name);
        group.setId(Integer.parseInt(id));
        groupDao.update(group);
    }

    @Override
    protected void delete() {
        Scanner scanner = new Scanner(System.in);
        String id = OperationUtil.getLineFromUser(scanner, "Id grupy do usunięcia");

        groupDao.delete(Integer.parseInt(id));
    }
}
