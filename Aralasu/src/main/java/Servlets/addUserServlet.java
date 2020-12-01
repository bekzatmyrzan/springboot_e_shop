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

@WebServlet(value = "/addUserServlet")
public class addUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        try {
            if (DBManager.is_user_exist(email)){
                error = "exist";
            }
            if (!password.equals(repassword)){
                error = "password";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (error.equals("")){
            String full_name = request.getParameter("full_name");
            String birth_date = request.getParameter("birth_date");
            String picture_url = "https://resize.indiatvnews.com/en/resize/newbucket/715_-/2020/02/gettyimages-467637912-594x594-1581313979.jpg";
            User user = new User(email,password,full_name,birth_date,picture_url);
            DBManager.addUser(user);
            response.sendRedirect("/home");
        }
        else {
            response.sendRedirect("/registrationServlet?" + error);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
