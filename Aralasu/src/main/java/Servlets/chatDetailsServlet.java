package Servlets;

import DB.Chat;
import DB.DBManager;
import DB.Message;
import DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/chatDetailsServlet")
public class chatDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            Long chat_id = Long.parseLong(request.getParameter("chat_id"));
            ArrayList<Message> messages = null;

            try {
                messages = DBManager.getMessages(chat_id);
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                DBManager.messageReaded(chat_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("chatDetails.jsp").forward(request, response);
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
