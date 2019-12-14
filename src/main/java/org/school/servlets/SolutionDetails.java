package org.school.servlets;

import org.school.Exercise;
import org.school.Solution;
import org.school.User;
import org.school.dao.ExerciseDao;
import org.school.dao.SolutionDao;
import org.school.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/soldetails")
public class SolutionDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("i"));
        int n=Integer.parseInt(getServletContext().getInitParameter("numbersolutions"));

        SolutionDao sdao = new SolutionDao();
        Solution s = new Solution();
        s=sdao.read(id);
        request.setAttribute("i", id);

        //get user name
        UserDao udao = new UserDao();
        User u = udao.read(s.getUserId());
        String username = u.getUserName();
        request.setAttribute("username", username);


        //get exercise title
        ExerciseDao edao = new ExerciseDao();
        Exercise e = edao.read(s.getExerciseId());
        String exercisetitle = e.getTitle();
        request.setAttribute("exercisetitle", exercisetitle);

        getServletContext().getRequestDispatcher("/soldetails.jsp").forward(request, response);
    }
}
