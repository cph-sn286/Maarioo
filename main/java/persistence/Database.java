package persistence;

import domain.MarioException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;
    private final String USER;
    private final String PASSWORD;
    private final String URL;

    public Database(String user, String password, String url) throws MarioException {
        USER = user;
        PASSWORD = password;
        URL = url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new MarioException("Kære Mario, der er sket en fejl i Database Constructor");
        }
    }

    public Connection connect() throws MarioException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new MarioException("Kære Mario, der er sket en fejl i Connection");

        }
        return connection;
    }

}
