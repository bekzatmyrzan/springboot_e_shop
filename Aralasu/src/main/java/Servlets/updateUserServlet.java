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

@WebServlet(value = "/updateUserServlet")
public class updateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/profileServlet";
        String sector = request.getParameter("sector");
        String error = "";
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (sector.equals("1")) {//full name and birthdate
            String email = user.getEmail();
            String full_name = request.getParameter("full_name");
            String birth_date = request.getParameter("birth_date");
            user.setEmail(email);
            user.setFull_name(full_name);
            user.setBirth_date(birth_date);
        } else if (sector.equals("2")) {//picture_url
            String picture_url = request.getParameter("picture_url");
            user.setPicture_url(picture_url);
        } else if (sector.equals("3")) {//update password
            String old_password = request.getParameter("old_password");
            if (old_password.equals(user.getPassword())) {
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                if (password.equals(repassword)) {
                    user.setPassword(password);
                } else {
                    error = "repassword";
//                    redirect += "?" + error;
                    request.setAttribute("repassword", repassword);
                }
            } else {
                error = "old";
//                redirect += "?" + error;
                request.setAttribute("error", error);
            }
        }
        if (error.equals("")) {
            try {
                DBManager.saveUser(user);
//                redirect = "/indexServlet";
//                response.sendRedirect(redirect);
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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
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
            request.getRequestDispatcher("/profile.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
