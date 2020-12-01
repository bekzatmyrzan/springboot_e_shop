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

@WebServlet(value = "/indexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            ArrayList<Post> posts = null;
            try {
                posts = DBManager.getPosts();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else if (email!=null && password!=null) {
            try {
                User user = DBManager.user_check_for_login(email, password);
                if (user != null) {
                    ArrayList<Post> posts = DBManager.getPosts();
                    request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
                    request.setAttribute("posts", posts);
                    request.getSession().setAttribute("CURRENT_USER",user);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    error = "exist";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            ArrayList<Post> posts = null;
            try {
                posts = DBManager.getPosts();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            User user = (User)request.getSession().getAttribute("CURRENT_USER");
            try {
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
