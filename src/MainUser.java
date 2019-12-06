import java.util.Scanner;

class MainUser {

    public static void main(String[] args) {
        DataManager userManager = new UserManager();
        userManager.run();
    }

    public static class UserManager extends DataManager {
        UserDao userDao;

        public UserManager() {
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
            String userName = getLineFromUser(scanner, "Imię użytkownika");
            String email = getLineFromUser(scanner, "Email użytkownika");
            String password = getLineFromUser(scanner, "Hasło użytkownika");

            User user = new User(userName, email, password);

            userDao.create(user);
        }

        @Override
        protected void edit() {
            Scanner scanner = new Scanner(System.in);
            String id = getLineFromUser(scanner, "Id użytkownika do edycji");
            String userName = getLineFromUser(scanner, "Imię użytkownika");
            String email = getLineFromUser(scanner, "Email użytkownika");
            String password = getLineFromUser(scanner, "Hasło użytkownika");

            User user = new User(userName, email, password);
            user.setId(Integer.parseInt(id));

            userDao.update(user);
        }

        @Override
        protected void delete() {
            Scanner scanner = new Scanner(System.in);
            String id = getLineFromUser(scanner, "Id użytkownika do usunięcia");
            userDao.delete(Integer.parseInt(id));
        }
    }
}
