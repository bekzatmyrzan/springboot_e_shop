package servlets;

import DB.DBManager;
import DB.New;
import DB.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<New> news = null;
        try {
            news = DBManager.getNews();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("news", news);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
