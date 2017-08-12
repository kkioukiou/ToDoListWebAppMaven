package src.WorkWithDatabase;

import src.WorkPackage.ToDoListItem;

import java.sql.*;

public class WorkWithDataBase {

    private final String URL = "jdbc:mysql://localhost:3306/ToDoListWebApp";
    private final String USERNAME = "root";
    private final String USERPASSWORD = "1560";

    public Connection connection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection
                    (URL, USERNAME, USERPASSWORD);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return con;
    }

    public void insertNewItemToDataBase(ToDoListItem toDoListItem){

        try {
            PreparedStatement ps = connection().prepareStatement
                    ("INSERT ToDoListWebApp.items (id, String_item) VALUES (?, ?)");

            ps.setInt(1, toDoListItem.getId());
            ps.setString(2, toDoListItem.getValue());

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your insert query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItemFromDataBase(int id){

        try {
            PreparedStatement ps = connection().prepareStatement
                    ("DELETE FROM ToDoListWebApp.items WHERE id=?");

            ps.setInt(1, id);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your delete query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectAllItemsFromDataBase(){

        try {
            Statement statement = connection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM ToDoListWebApp.items;");

            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
