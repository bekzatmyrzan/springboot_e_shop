package Servlets;

import DB.DBManager;
import DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/sendRequestServlet")
public class sendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        Long another_user_id = Long.parseLong(request.getParameter("another_user_id"));
        User another_user = null;
        try {
            another_user = DBManager.getUser(another_user_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBManager.addFriendRequest(another_user, currentUser);
        response.sendRedirect("/friendsServlet?success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
