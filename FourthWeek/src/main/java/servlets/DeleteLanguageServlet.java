package servlets;

import DB.DBManager;
import DB.Language;
import DB.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/deleteLanguage")
public class DeleteLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Language language = null;
        try {
            language = DBManager.getLanguage(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(language!=null){
            DBManager.deleteLanguage(id);
        }

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
