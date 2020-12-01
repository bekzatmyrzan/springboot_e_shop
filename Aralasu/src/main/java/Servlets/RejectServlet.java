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

@WebServlet(value = "/rejectServlet")
public class RejectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");//4 Edon
        Long sender_id = Long.parseLong(request.getParameter("sender_id"));//1 Bekzat
        User another_user = null;
        try {
            another_user = DBManager.getUser(sender_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBManager.removeFromFriendsRequests(another_user,currentUser);//todo
        response.sendRedirect("/friendsServlet?rejsuccess");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
