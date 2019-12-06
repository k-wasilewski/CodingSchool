import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;

class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solutions(created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT id, created, updated, description, exercise_id, user_id FROM solutions WHERE id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solutions SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ? WHERE id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT id, created, updated, description, exercise_id, user_id FROM solutions";
    private static final String FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY =
            "SELECT id, created, updated, description, exercise_id, user_id FROM solutions WHERE exercise_id = ?";

    public Solution create(Solution solution) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement ps = conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setTimestamp(1, new Timestamp(solution.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(solution.getUpdated().getTime()));
            ps.setString(3, solution.getDescription());
            ps.setInt(4, solution.getExerciseId());
            ps.setInt(5, solution.getUserId());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                final int solutionId = gk.getInt(1);
                solution.setId(solutionId);

                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Solution read(int solutionId) {
        try (Connection conn = DBUtil.connection();
             final PreparedStatement ps = conn.prepareStatement(READ_SOLUTION_QUERY);
        ) {
            ps.setInt(1, solutionId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUserId(rs.getInt("user_id"));

                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);) {

            statement.setTimestamp(1, new Timestamp(solution.getCreated().getTime()));
            statement.setTimestamp(2, new Timestamp(solution.getUpdated().getTime()));
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExerciseId());
            statement.setInt(5, solution.getUserId());
            statement.setInt(6, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY)) {
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solution[] findAll() {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);) {
            Solution[] solutions = new Solution[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("user_id"));

                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findAllByExerciseId(int exerciseId) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY);) {
            statement.setInt(1, exerciseId);
            Solution[] solutions = new Solution[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("user_id"));

                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] addToArray(Solution u, Solution[] solutions) {
        Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolutions[solutions.length] = u;
        return tmpSolutions;
    }
}
