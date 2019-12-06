import java.sql.Connection;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();

        User newUser = new User("test", "test@test.com", "test123");
        User createdUser = userDao.create(newUser);

        System.out.println("Create user: " + createdUser);


        User readUser = userDao.read(100);
        System.out.println("Read user: " + readUser);

        System.out.println("--------------------");
        User userBeforeUpdate = userDao.read(2);
        System.out.println("User before update: " + userBeforeUpdate);

        User userToUpdate = new User("abc", "def@test.com", "abc12345");
        userToUpdate.setId(2);

        userDao.update(userToUpdate);

        User userAfterUpdate = userDao.read(2);
        System.out.println("User after update: " + userAfterUpdate);


        System.out.println("--------------------");
        User userBeforeDelete = userDao.read(5);
        System.out.println("User before delete: " + userBeforeDelete);

        userDao.delete(5);

        User userAfterDelete = userDao.read(5);
        System.out.println("User after delete: " + userAfterDelete);
    }
}
