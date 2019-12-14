package org.school.servlets;

import org.school.User;
import org.school.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/groupusers")
public class GroupUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupid = Integer.parseInt(request.getParameter("i"));

        UserDao udao = new UserDao();
        User[] users = udao.findAllByGroupId(groupid);
        System.out.println(Arrays.toString(users));
        request.setAttribute("users", users);
        request.setAttribute("gid", groupid);
        getServletContext().getRequestDispatcher("/groupusers.jsp").forward(request, response);
    }
}
