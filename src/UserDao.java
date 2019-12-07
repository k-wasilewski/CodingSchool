import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT id, username, email, password FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT id, username, email, password FROM users";
    private static final String FIND_ALL_USERS_BY_GROUP_ID_QUERY =
            "SELECT id, username, email, password FROM users WHERE group_id = ?";

    public User create(User user) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement ps = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                final int userId = gk.getInt(1);
                user.setId(userId);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User read(int userId) {
        try (Connection conn = DBUtil.connection();
             final PreparedStatement ps = conn.prepareStatement(READ_USER_QUERY);
        ) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(User user) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);) {
            User[] users = new User[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User[] findAllByGroupId(int groupId) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_BY_GROUP_ID_QUERY);) {
            statement.setInt(1, groupId);
            User[] users = new User[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
}
