package DB;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class DBManager {

    private static Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBManager() throws SQLException {
    }

    public static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("picture_url")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUser(Long id) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from users where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("picture_url")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users.get(0);
    }

    public static boolean is_user_exist(String email) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from users where email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("picture_url")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (users.size() > 0) ? true : false;
    }

    public static User user_check_for_login(String email, String password) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from users where email = ? and password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("picture_url")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (users.size() > 0) ? users.get(0) : null;
    }

    public static boolean addUser(User user) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into users (id,email,password,full_name,birth_date,picture_url)" +
                    "values (null ,?,?,?,?,?)" +
                    "");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFull_name());
            preparedStatement.setString(4, user.getBirth_date());
            preparedStatement.setString(5, user.getPicture_url());

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static void saveUser(User user) throws SQLException {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "update users " +
                    "set " +
                    "email = ?," +
                    "password = ?," +
                    "full_name = ?," +
                    "birth_date = ?," +
                    "picture_url = ? where id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFull_name());
            preparedStatement.setString(4, user.getBirth_date());
            preparedStatement.setString(5, user.getPicture_url());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean addPost(Post post) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into posts (id,author_id,title,short_content,content,post_date)" +
                    "values (null ,?,?,?,?,?)" +
                    "");
            preparedStatement.setLong(1, post.getAuthor().getId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getShort_content());
            preparedStatement.setString(4, post.getContent());
            preparedStatement.setTimestamp(5, post.getPost_date());

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static User getAuthor(Long id) throws SQLException {
        User author = new User();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from users where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                author = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public static ArrayList<Post> getPosts() throws SQLException {
        ArrayList<Post> posts = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from posts");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(
                        new Post(
                                resultSet.getLong("id"),
                                getAuthor(resultSet.getLong("author_id")),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getPostsById(Long id) throws SQLException {
        ArrayList<Post> posts = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from posts where author_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(
                        new Post(
                                resultSet.getLong("id"),
                                getAuthor(resultSet.getLong("author_id")),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<User> latest_birthdays_user() throws SQLException {
        ArrayList<User> users = getUsers();
        ArrayList<User> result_users = new ArrayList<>();
        for (User user : users) {
            LocalDate birth_date = LocalDate.of(
                    Calendar.getInstance().get(Calendar.YEAR),
                    Integer.parseInt("" + user.getBirth_date().charAt(5) + user.getBirth_date().charAt(6)),
                    Integer.parseInt("" + user.getBirth_date().charAt(8) + user.getBirth_date().charAt(9)));
            if (Period.between(birth_date, LocalDate.now()).getMonths() < 1 && Period.between(birth_date, LocalDate.now()).getDays() < 0) {
                result_users.add(user);
            }
        }
        for (int i = 0; i < result_users.size() - 1; i++) {
            for (int j = i + 1; j < result_users.size(); j++) {
                LocalDate birth_date1 = LocalDate.of(
                        Calendar.getInstance().get(Calendar.YEAR),
                        Integer.parseInt("" + result_users.get(i).getBirth_date().charAt(5) + result_users.get(i).getBirth_date().charAt(6)),
                        Integer.parseInt("" + result_users.get(i).getBirth_date().charAt(8) + result_users.get(i).getBirth_date().charAt(9)));

                LocalDate birth_date2 = LocalDate.of(
                        Calendar.getInstance().get(Calendar.YEAR),
                        Integer.parseInt("" + result_users.get(j).getBirth_date().charAt(5) + result_users.get(j).getBirth_date().charAt(6)),
                        Integer.parseInt("" + result_users.get(j).getBirth_date().charAt(8) + result_users.get(j).getBirth_date().charAt(9)));
                if (Period.between(LocalDate.now(), birth_date1).getDays() > Period.between(LocalDate.now(), birth_date2).getDays()) {
                    Collections.swap(result_users, i, j);
                }
            }
        }
        return result_users;
    }

    public static boolean addFriendRequest(User user, User requestUser) {
        int rows = 0;
        String s;
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = formatter.format(new Date());
        Timestamp sent_time = Timestamp.valueOf(s);

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into friends_requests (id,user_id,request_sender_id,sent_time)" +
                    "values (null ,?,?,?)" +
                    "");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, requestUser.getId());
            preparedStatement.setTimestamp(3, sent_time);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean addToFriends(User user, User friend) {
        int rows = 0;
        String s;
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = formatter.format(new Date());
        Timestamp added_time = Timestamp.valueOf(s);

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into friends (id,user_id,friend_id,added_time)" +
                    "values (null ,?,?,?)" +
                    "");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, friend.getId());
            preparedStatement.setTimestamp(3, added_time);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Chat> getChats(Long user_id, Long currentUserId) throws SQLException {
        ArrayList<Chat> chats = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from chats where user_id = ? or opponent_user_id = ?");
            statement.setLong(1, user_id);
            statement.setLong(2, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Chat chat = new Chat(resultSet.getLong("id"),
                        getUser(resultSet.getLong("user_id")),
                        getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        countOfNewMessage(resultSet.getLong("id"),getUser(resultSet.getLong("user_id") == currentUserId?resultSet.getLong("user_id"):resultSet.getLong("opponent_user_id"))));
                if (chat.getOpponent_user().getId().equals(currentUserId)) {
                    User temp = chat.getUser();
                    chat.setUser(chat.getOpponent_user());
                    chat.setOpponent_user(temp);
                }
                chats.add(chat);
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }

    public static Chat getChat(Long chat_id) throws SQLException {
        ArrayList<Chat> chats = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from chats where id = ?");
            statement.setLong(1, chat_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                chats.add(
                        new Chat(
                                resultSet.getLong("id"),
                                getUser(resultSet.getLong("user_id")),
                                getUser(resultSet.getLong("opponent_user_id")),
                                resultSet.getTimestamp("created_date"),
                                resultSet.getString("latest_message_text"),
                                resultSet.getTimestamp("latest_message_time"),
                                countOfNewMessage(chat_id,getUser(resultSet.getLong("user_id")))));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats.get(0);
    }

    public static Chat getChat(User user, User opponent_user) throws SQLException {
        ArrayList<Chat> chats = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from chats where (user_id = ? and opponent_user_id = ?) or (user_id = ? and opponent_user_id = ?)");
            statement.setLong(1, user.getId());
            statement.setLong(2, opponent_user.getId());
            statement.setLong(3, opponent_user.getId());
            statement.setLong(4, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                chats.add(
                        new Chat(
                                resultSet.getLong("id"),
                                getUser(resultSet.getLong("user_id")),
                                getUser(resultSet.getLong("opponent_user_id")),
                                resultSet.getTimestamp("created_date"),
                                resultSet.getString("latest_message_text"),
                                resultSet.getTimestamp("latest_message_time"),
                                countOfNewMessage(resultSet.getLong("id"),getUser(resultSet.getLong("user_id") == user.getId()?resultSet.getLong("user_id"):resultSet.getLong("opponent_user_id")))));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats.get(0);
    }

    public static ArrayList<Message> getMessages(Long chat_id) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from messages where chat_id = ?");
            statement.setLong(1, chat_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(
                        new Message(
                                resultSet.getLong("id"),
                                getChat(resultSet.getLong("chat_id")),
                                getUser(resultSet.getLong("user_id")),
                                getUser(resultSet.getLong("sender_id")),
                                resultSet.getString("message_text"),
                                resultSet.getBoolean("read_by_receiver"),
                                resultSet.getTimestamp("sent_date")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static boolean addChat(User user, User opponent_user, String latest_message_text, Timestamp latest_message_time) {
        int rows = 0;

        String s;
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = formatter.format(new Date());
        Timestamp created_date = Timestamp.valueOf(s);

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into chats (id,user_id,opponent_user_id,created_date,latest_message_text,latest_message_time)" +
                    "values (null ,?,?,?,?,?)" +
                    "");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, opponent_user.getId());
            preparedStatement.setTimestamp(3, created_date);
            preparedStatement.setString(4, latest_message_text);
            preparedStatement.setTimestamp(5, latest_message_time);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean addMessage(User user, User opponent_user, String message_text, Boolean read_by_receiver) {
        int rows = 0;
        String s;
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = formatter.format(new Date());
        Timestamp sent_date = Timestamp.valueOf(s);

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "select count(*) as row_count from chats where (user_id = ? and opponent_user_id = ?) or (user_id = ? and opponent_user_id = ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, opponent_user.getId());
            preparedStatement.setLong(3, opponent_user.getId());
            preparedStatement.setLong(4, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            rows = resultSet.getInt("row_count");
            System.out.println(rows + "here");
            preparedStatement.close();

            if (rows == 0) {//no existing chat
                addChat(user, opponent_user, message_text, sent_date);
                System.out.println("WWW");
            }
            PreparedStatement preparedStatement2 = con.prepareStatement("" +
                    "INSERT into messages (id,chat_id,user_id,sender_id,message_text,read_by_receiver,sent_date)" +
                    "values (null ,?,?,?,?,?,?)" +
                    "");
            preparedStatement2.setLong(1, getChat(user, opponent_user).getId());
            preparedStatement2.setLong(2, opponent_user.getId());
            preparedStatement2.setLong(3, user.getId());
            preparedStatement2.setString(4, message_text);
            preparedStatement2.setBoolean(5, read_by_receiver);
            preparedStatement2.setTimestamp(6, sent_date);
            System.out.println("RRR");

            rows = preparedStatement2.executeUpdate();
            preparedStatement2.close();

            PreparedStatement preparedStatement3 = con.prepareStatement("" +
                    "update chats " +
                    "set " +
                    "latest_message_text = ?," +
                    "latest_message_time = ? where id = ?");
            preparedStatement3.setString(1, message_text);
            preparedStatement3.setTimestamp(2, sent_date);
            preparedStatement3.setLong(3, getChat(user, opponent_user).getId());
            preparedStatement3.execute();
            preparedStatement3.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static int countOfNewMessage(Long chat_id,User user) {
        int rows = 0;
        String s;
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = formatter.format(new Date());
        Timestamp sent_date = Timestamp.valueOf(s);

        try {
            //select count(*) as row_count from chats where (user_id = ? and opponent_user_id = ?) or (user_id = ? and opponent_user_id = ?)
            PreparedStatement preparedStatement2 = con.prepareStatement("" +
                    "select count(read_by_receiver) as row_count from messages where chat_id = ? and user_id = ? and read_by_receiver = false" +
                    "");
            preparedStatement2.setLong(1,chat_id);
            preparedStatement2.setLong(2,user.getId());
            ResultSet resultSet = preparedStatement2.executeQuery();
            resultSet.next();
            rows = resultSet.getInt("row_count");
            System.out.println("VVV");
            preparedStatement2.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void messageReaded(Long chat_id) throws SQLException {//Edon Bekzat 2 1
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "update messages " +
                    "set " +
                    "read_by_receiver = ? where chat_id = ?");
            preparedStatement.setBoolean(1, true);
            preparedStatement.setLong(2, chat_id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean removeFromFriends(User user, User friend) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "delete from friends where user_id = ? and friend_id = ?");
            System.out.println(user.getId());
            System.out.println(friend.getId());
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, friend.getId());
            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean removeFromFriendsRequests(User friend, User user) {//Bekzat Edon
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "delete from friends_requests where user_id = ? and request_sender_id = ?");
            System.out.println(user.getId());
            System.out.println(friend.getId());
            preparedStatement.setLong(1, user.getId());//4
            preparedStatement.setLong(2, friend.getId());//1
            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<User> getFriends(Long id) throws SQLException {
        ArrayList<User> friends = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from friends where user_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                friends.add(getUser(resultSet.getLong("friend_id")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friends;
    }

    public static ArrayList<User> getFriendsRequests(Long user_id) throws SQLException {
        ArrayList<User> friendsRequests = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from friends_requests where user_id = ?");
            statement.setLong(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                friendsRequests.add(getUser(resultSet.getLong("request_sender_id")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendsRequests;
    }

    public static ArrayList<User> searchUsers(String str, User currentUser) throws SQLException {
        ArrayList<User> searchUsers = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from users where full_name like ? and id <> ?");
            statement.setString(1, "%" + str + "%");
            statement.setLong(2, currentUser.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                searchUsers.add(getUser(resultSet.getLong("id")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchUsers;
    }

    public static int isFriends(Long id1, Long id2) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "select count(*) as row_count from friends where user_id = ? and friend_id = ?");
            preparedStatement.setLong(1, id1);
            preparedStatement.setLong(2, id2);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            rows = resultSet.getInt("row_count");
            System.out.println(rows);

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

}
