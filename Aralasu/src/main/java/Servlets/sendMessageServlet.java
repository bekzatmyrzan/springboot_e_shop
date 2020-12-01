package Servlets;

import DB.DBManager;
import DB.Message;
import DB.Post;
import DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(value = "/sendMessageServlet")
public class sendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        Long opponent_user_id = Long.parseLong(request.getParameter("opponent_user_id"));//Edon
        String message_text = request.getParameter("message_text");
        String fromJSP = request.getParameter("fromJSP");
        Boolean read_by_receiver = false;

        User opponent_user = null;
        try {
            opponent_user = DBManager.getUser(opponent_user_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        Message message = new Message(null,null,user,sender,message_text,read_by_receiver,sent_date);
        DBManager.addMessage(currentUser,opponent_user,message_text,read_by_receiver);


        if (fromJSP.equals("anotherUserPage")) {
            ArrayList<Post> posts = null;
            try {
                posts = DBManager.getPostsById(opponent_user_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int isFriends = DBManager.isFriends(opponent_user_id, currentUser.getId());
            request.setAttribute("isFriends", isFriends);
            request.setAttribute("another_user", opponent_user);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/anotherUserPage.jsp").forward(request, response);
        }else if (fromJSP.equals("chatDetails")){
            Long chat_id;
            ArrayList<Message> messages = null;
            try {
                chat_id = DBManager.getChat(currentUser,opponent_user).getId();
                messages = DBManager.getMessages(chat_id);
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("chatDetails.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
