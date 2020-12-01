package servlets;

import DB.DBManager;
import DB.New;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/detailsNewServlet")
public class NewDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        New novost = null;
        try {
            novost = DBManager.getNew(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("novost", novost);
        request.getRequestDispatcher("/detailsNew.jsp").forward(request, response);
    }
}
