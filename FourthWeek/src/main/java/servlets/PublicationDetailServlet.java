package servlets;

import DB.DBManager;
import DB.Language;
import DB.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/detailsPublicationServlet")
public class PublicationDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Publication publication = null;
        try {
            publication = DBManager.getPublication(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("publication", publication);
        request.getRequestDispatcher("/detailsPublication.jsp").forward(request, response);
    }
}
