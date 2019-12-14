package org.school.servlets;

import org.school.Exercise;
import org.school.dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allexercises")
public class AllExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao edao = new ExerciseDao();
        Exercise[] exs = edao.findAll();
        request.setAttribute("exs", exs);

        getServletContext().getRequestDispatcher("/allexercisesadmin.jsp").forward(request, response);
    }
}
