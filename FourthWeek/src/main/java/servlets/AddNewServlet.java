package servlets;

import DB.DBManager;
import DB.Language;
import DB.New;
import DB.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(value = "/addNew")
public class AddNewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String short_content = request.getParameter("short_content");
        String content = request.getParameter("content");
        Timestamp post_date = Timestamp.valueOf(request.getParameter("post_date"));
        String picture_url = request.getParameter("picture_url");
        Language language = new Language();
        try {
            language = DBManager.getLanguage(Long.valueOf(request.getParameter("language_id")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Publication publication = new Publication();
        try {
            publication = DBManager.getPublication(Long.valueOf(request.getParameter("publication_id")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        New novost = new New(null, title,short_content,content,post_date,picture_url,language,publication);

        DBManager.addPublication(publication);
        response.sendRedirect("/addNew?success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addNew.jsp").forward(request, response);
    }
}
