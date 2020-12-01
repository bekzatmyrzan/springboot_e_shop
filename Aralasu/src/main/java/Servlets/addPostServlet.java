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
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(value = "/addPostServlet")
public class addPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long author_id = Long.parseLong(request.getParameter("author_id"));
        String title = request.getParameter("title");
        String short_content = request.getParameter("short_content");
        String content = request.getParameter("content");

        String s;
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = formatter.format(new Date());
        Timestamp post_date = Timestamp.valueOf(s);

        User author = null;
        try {
            author = DBManager.getAuthor(author_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Post post = new Post(null, author, title,short_content, content, post_date);

        DBManager.addPost(post);
        ArrayList<Post> posts = null;
        try {
            posts = DBManager.getPosts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            request.setAttribute("latest_birthdays_user",DBManager.latest_birthdays_user());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("posts",posts);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
