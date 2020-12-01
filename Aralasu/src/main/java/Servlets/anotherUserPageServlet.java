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

@WebServlet(value = "/anotherUserPageServlet")
public class anotherUserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
            Long another_user_id = Long.parseLong(request.getParameter("another_user_id"));
            ArrayList<Post> posts = null;
            User another_user = null;
            try {
                another_user = DBManager.getUser(another_user_id);
                posts = DBManager.getPostsById(another_user_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int isFriends = DBManager.isFriends(another_user_id,currentUser.getId());
            request.setAttribute("isFriends",isFriends);
            request.setAttribute("another_user", another_user);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("anotherUserPage.jsp").forward(request, response);
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
