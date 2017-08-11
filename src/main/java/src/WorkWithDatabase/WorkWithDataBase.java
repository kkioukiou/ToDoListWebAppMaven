package src.WorkWithDatabase;

import src.WorkPackage.ToDoListItem;

import java.sql.*;

public class WorkWithDataBase {

    private final String  URL = "jdbc:mysql://localhost:3306/ToDoListWebApp";
    private final String USERNAME = "root";
    private final String USERPASSWORD = "1560";

    public void insertNewItemToDataBase(ToDoListItem toDoListItem){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection  con = DriverManager.getConnection
                    (URL, USERNAME, USERPASSWORD);

            PreparedStatement ps = con.prepareStatement
                    ("INSERT ToDoListWebApp.items (id, String_item) VALUES (?, ?)");

            ps.setInt(1, toDoListItem.getId());
            ps.setString(2, toDoListItem.getValue());

            int i = ps.executeUpdate();

            con.close();

            if (i > 0){
                System.out.println("Your insert query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItemFromDataBase(int id){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection  con = DriverManager.getConnection
                    (URL, USERNAME, USERPASSWORD);

            PreparedStatement ps = con.prepareStatement
                    ("DELETE FROM ToDoListWebApp.items WHERE id=?");

            ps.setInt(1, id);

            int i = ps.executeUpdate();

            con.close();

            if (i > 0){
                System.out.println("Your delete query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectAllItemsFromDataBase(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection  con = DriverManager.getConnection
                    (URL, USERNAME, USERPASSWORD);

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM ToDoListWebApp.items;");

            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
