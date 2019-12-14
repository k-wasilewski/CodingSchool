package org.school.dao;

import org.school.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExerciseDao {
    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT id, title, description FROM exercise WHERE id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISE_QUERY =
            "SELECT id, title, description FROM exercise";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement ps = conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, exercise.getTitle());
            ps.setString(2, exercise.getDescription());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                exercise.setId(id);

                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Exercise read(int exerciseId) {
        try (Connection conn = DBUtil.connection();
             final PreparedStatement ps = conn.prepareStatement(READ_EXERCISE_QUERY);
        ) {
            ps.setInt(1, exerciseId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));

                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Exercise exercise) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);) {

            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY)) {
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise[] findAll() {
        try (Connection conn = DBUtil.connection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_QUERY);) {
            Exercise[] exercises = new Exercise[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));

                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Exercise[] addToArray(Exercise u, Exercise[] exercises) {
        Exercise[] tmpExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercises[exercises.length] = u;
        return tmpExercises;
    }

}
