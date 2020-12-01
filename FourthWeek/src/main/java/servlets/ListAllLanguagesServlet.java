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
import java.util.ArrayList;

@WebServlet(value = "/listAllLanguages")
public class ListAllLanguagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Language> languages = null;
        try {
            languages = DBManager.getLanguages();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//****
        request.setAttribute("languages", languages);
        request.getRequestDispatcher("/listAllLanguages.jsp").forward(request, response);
    }
}
