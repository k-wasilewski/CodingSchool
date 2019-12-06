import java.util.Scanner;

class MainGroup {
    public static void main(String[] args) {
        DataManager userManager = new GroupManager();
        userManager.run();
    }

    private static class GroupManager extends DataManager {
        GroupDao groupDao;

        public GroupManager() {
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
            String name = getLineFromUser(scanner, "Nazwa grupy:");

            Group group = new Group(name);
            groupDao.create(group);
        }

        @Override
        protected void edit() {
            Scanner scanner = new Scanner(System.in);
            String id = getLineFromUser(scanner, "Id grupy do modyfikacji:");
            String name = getLineFromUser(scanner, "Nazwa grupy:");

            Group group = new Group(name);
            group.setId(Integer.parseInt(id));
            groupDao.update(group);
        }

        @Override
        protected void delete() {
            Scanner scanner = new Scanner(System.in);
            String id = getLineFromUser(scanner, "Id grupy do usunięcia");

            groupDao.delete(Integer.parseInt(id));
        }
    }
}
