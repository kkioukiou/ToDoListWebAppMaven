package src.DbContext;

import com.google.gson.Gson;
import src.Models.ToDoListItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbContext {

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

    public void insertNewItem(String toDoListItem){

        try {
            PreparedStatement ps = connection().prepareStatement
                    ("INSERT ToDoListWebApp.items (String_item) VALUES (?)");

//            ps.setInt(1, toDoListItem.getId());
            ps.setString(1, toDoListItem);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your insert query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id){

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

    public String selectAllItems(){

        try {
            Statement statement = connection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM ToDoListWebApp.items;");

            List<ToDoListItem> list = new ArrayList<ToDoListItem>();

            try {
                while (resultSet.next()){
                    list.add(new ToDoListItem(resultSet.getInt(1), resultSet.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String json = new Gson().toJson(list);

            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}