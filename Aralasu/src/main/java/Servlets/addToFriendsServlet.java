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

@WebServlet(value = "/addToFriendsServlet")
public class addToFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        Long friend_id = Long.parseLong(request.getParameter("friend_id"));
        User friend_user = null;
        try {
            friend_user = DBManager.getUser(friend_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBManager.addToFriends(friend_user, currentUser);
        DBManager.addToFriends(currentUser, friend_user);
        response.sendRedirect("/friendsServlet?addsuccess");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
