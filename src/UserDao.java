import com.mysql.cj.xdevapi.DbDoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class UserDao {
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

    public User create(User user) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement ps = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if(gk.next()) {
                final int userId = gk.getInt(1);
                user.setId(userId);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
