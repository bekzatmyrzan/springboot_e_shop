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

@WebServlet(value = "/savePublicationServlet")
public class SavePublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double rating = Double.valueOf(request.getParameter("rating"));
        Publication publication = null;
        try {
            publication = DBManager.getPublication(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (publication != null) {

            publication.setName(name);
            publication.setDescription(description);
            publication.setRating(rating);

            try {
                DBManager.savePublication(publication);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        response.sendRedirect("/detailsPublication?id=" + id + "&success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
