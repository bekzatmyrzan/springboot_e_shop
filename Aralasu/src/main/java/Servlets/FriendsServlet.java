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

@WebServlet(value = "/friendsServlet")
public class FriendsServlet extends HttpServlet {

    public void removeFriendsFromRequestsList(ArrayList<User> friends, ArrayList<User> friendsRequests) {
        for (int i = 0; i < friendsRequests.size(); i++) {
            for (int j = 0; j < friends.size(); j++) {
                if (friends.get(j).getFull_name().equals(friendsRequests.get(i).getFull_name())) {
                    friendsRequests.remove(i);
                    return;
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
            String str = request.getParameter("search");

            ArrayList<User> searchUsers = null;
            ArrayList<User> friends = null;
            ArrayList<User> friendsRequests = null;
            try {
                request.setAttribute("latest_birthdays_user", DBManager.latest_birthdays_user());
                searchUsers = DBManager.searchUsers(str, currentUser);
                friends = DBManager.getFriends(currentUser.getId());
                friendsRequests = DBManager.getFriendsRequests(currentUser.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            removeFriendsFromRequestsList(friends, friendsRequests);
            request.setAttribute("searchUsers", searchUsers);
            request.setAttribute("friends", friends);
            request.setAttribute("friendsRequests", friendsRequests);

            request.getRequestDispatcher("/friends.jsp").forward(request, response);
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
