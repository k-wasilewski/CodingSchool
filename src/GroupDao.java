import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class GroupDao {
    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO user_groups(name) VALUES (?)";
    private static final String READ_GROUP_QUERY =
            "SELECT id, name FROM user_groups WHERE id = ?";
    private static final String UPDATE_GROUP_QUERY =
            "UPDATE user_groups SET name = ? WHERE id = ?";
    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM user_groups WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT id, name FROM user_groups";

    public Group create(Group group) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement ps = conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, group.getName());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int groupId = gk.getInt(1);
                group.setId(groupId);

                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Group read(int userId) {
        try (Connection conn = DBUtil.connection();
             final PreparedStatement ps = conn.prepareStatement(READ_GROUP_QUERY);
        ) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));

                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Group group) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);) {

            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY)) {
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group[] findAll() {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);) {
            Group[] groups = new Group[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));

                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Group[] addToArray(Group g, Group[] array) {
        Group[] tmpArray = Arrays.copyOf(array, array.length + 1);
        tmpArray[array.length] = g;
        return tmpArray;
    }
}
