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

@WebServlet(value = "/myPageServlet")
public class myPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            User user = (User)request.getSession().getAttribute("CURRENT_USER");
            ArrayList<Post> posts = null;
            try {
                posts = DBManager.getPostsById(user.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("myPage.jsp").forward(request, response);
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
