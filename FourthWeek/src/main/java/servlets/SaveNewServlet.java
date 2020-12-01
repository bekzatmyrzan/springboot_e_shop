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

@WebServlet(value = "/saveNewServlet")
public class SaveNewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
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


        New novost = null;
        try {
            novost = DBManager.getNew(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (novost != null) {

            novost.setTitle(title);
            novost.setShort_content(short_content);
            novost.setContent(content);
            novost.setPost_date(post_date);
            novost.setPicture_url(picture_url);
            novost.setLanguage(language);
            novost.setPublication(publication);

            try {
                DBManager.saveNew(novost);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        response.sendRedirect("/detailsNew?id=" + id + "&success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
