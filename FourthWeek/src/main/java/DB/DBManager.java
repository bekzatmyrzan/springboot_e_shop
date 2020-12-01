package DB;

import java.sql.*;
import java.util.ArrayList;

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


    public static ArrayList<Publication> getPublications() throws SQLException {
        ArrayList<Publication> publications = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from publications");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(
                        new Publication(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("rating")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publications;
    }

    public static Publication getPublication(Long id) throws SQLException {
        Publication publication = new Publication();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from publications where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publication = new Publication(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("rating"));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publication;
    }

    public static ArrayList<Language> getLanguages() throws SQLException {
        ArrayList<Language> languages = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from languages");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                languages.add(
                        new Language(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("code")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
    }

    public static Language getLanguage(Long id) throws SQLException {
        Language language = new Language();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from languages where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                language = new Language(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("code"));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

    public static ArrayList<New> getNews() throws SQLException {
        ArrayList<New> news = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from news");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.add(
                        new New(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date"),
                                resultSet.getString("picture_url"),
                                getLanguage(resultSet.getLong("language_id")),
                                getPublication(resultSet.getLong("publication_id"))));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static New getNew(Long id) throws SQLException {
        New novost = new New();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("Select * from news where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                novost = new New(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date"),
                                resultSet.getString("picture_url"),
                                getLanguage(resultSet.getLong("language_id")),
                                getPublication(resultSet.getLong("publication_id")));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return novost;
    }

    public static boolean addLanguage(Language language) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into languages (id,name,code)" +
                    "values (null ,?,?)" +
                    "");
            setDataLanguage(language,preparedStatement);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean addPublication(Publication publication) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into publications (id,name,description,rating)" +
                    "values (null ,?,?,?)" +
                    "");
            setDataPublication(publication,preparedStatement);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static void savePublication(Publication publication) throws SQLException {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "update publications" +
                    "set " +
                    "name = ?," +
                    "description = ?," +
                    "rating = ? where id = ?");
            setDataPublication(publication,preparedStatement);
            preparedStatement.setLong(4, publication.getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean addNew(New novost) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "INSERT into news (id,title,short_content,content,post_date,picture_url,language_id,publication_id)" +
                    "values (null ,?,?,?,?,?,?,?)" +
                    "");
            setDataNew(novost, preparedStatement);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static void deleteLanguage(Long id) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "delete from languages where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNew(Long id) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "delete from news where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePublication(Long id) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "delete from publications where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveLanguage(Language language) throws SQLException {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "update languages" +
                    "set " +
                    "name = ?," +
                    "code = ? where id = ?");
            setDataLanguage(language,preparedStatement);
            preparedStatement.setLong(3, language.getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveNew(New novost) throws SQLException {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "update news" +
                    "set " +
                    "title = ?," +
                    "short_content = ?," +
                    "content = ?," +
                    "post_date = ?," +
                    "picture_url = ?," +
                    "language_id = ?," +
                    "publication_id = ? where id = ?");
            setDataNew(novost, preparedStatement);
            preparedStatement.setLong(8, novost.getId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setDataNew(New novost, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, novost.getTitle());
        preparedStatement.setString(2, novost.getShort_content());
        preparedStatement.setString(3, novost.getContent());
        preparedStatement.setTimestamp(4, novost.getPost_date());
        preparedStatement.setString(5, novost.getPicture_url());
        preparedStatement.setLong(6, novost.getLanguage().getId());
        preparedStatement.setLong(7, novost.getPublication().getId());
    }

    private static void setDataPublication(Publication publication, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,publication.getName());
        preparedStatement.setString(2,publication.getDescription());
        preparedStatement.setDouble(3,publication.getRating());
    }

    private static void setDataLanguage(Language language, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, language.getName());
        preparedStatement.setString(2, language.getCode());
    }

}
