package Servlets;

import DB.DBManager;
import DB.Post;
import DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/removeFromFriendsServlet")
public class removeFromFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            System.out.println("here");
            Long friend_id = Long.parseLong(request.getParameter("another_user_id"));
            Long user_id = Long.parseLong(request.getParameter("user_id"));
            System.out.println(friend_id + "WW");
            System.out.println(user_id + "EE");
            User user = null;
            User friend = null;
            try {
                user = DBManager.getUser(user_id);
                friend = DBManager.getUser(friend_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            DBManager.removeFromFriends(user,friend);
            // men -> Edon
            // ++
            // men
            DBManager.removeFromFriendsRequests(user,friend);
            DBManager.removeFromFriends(friend,user);
            response.sendRedirect("/indexServlet");
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
