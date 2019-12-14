package org.school.servlets;

import org.school.Group;
import org.school.dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/showallgroups")
public class ShowAllGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao gdao = new GroupDao();
        Group[] allgroups = gdao.findAll();
        List<Group> arrallgroups = Arrays.asList(allgroups);
        request.setAttribute("arrallgroups", arrallgroups);
        request.setAttribute("groupslength", allgroups.length);

        getServletContext().getRequestDispatcher("/allgroups.jsp").forward(request, response);
    }
}
