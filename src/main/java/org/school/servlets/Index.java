package org.school.servlets;

import org.school.Solution;
import org.school.dao.SolutionDao;
import org.school.dao.UserDao;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Arrays;

@WebServlet("/")
public class Index extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        SolutionDao sdao = new SolutionDao();
        UserDao udao = new UserDao();
        int n=Integer.parseInt(getServletContext().getInitParameter("numbersolutions"));
        Solution[] solutions=sdao.findRecent(n);
        request.setAttribute("solutions", solutions);

        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
