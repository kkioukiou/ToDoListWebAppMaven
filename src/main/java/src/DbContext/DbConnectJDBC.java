package src.DbContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/ToDoListWebApp";
    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "1560";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
        return con;
    }
}
