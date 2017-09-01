package src.DbContext;

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
                    ("INSERT ToDoListWebApp.items (itemValue) VALUES (?)");

            ps.setString(1, toDoListItem);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your insert query is success!");
            }

            connection().close();
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

            connection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ToDoListItem> selectAllItems(){

        try {
            Statement statement = connection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id,itemValue,itemChecked FROM ToDoListWebApp.items;");

            List<ToDoListItem> list = new ArrayList<ToDoListItem>();

            try {
                while (resultSet.next()){
                    list.add(new ToDoListItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection().close();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkedItem(int id, boolean check){
        try {
            PreparedStatement ps = connection().prepareStatement
                    ("UPDATE ToDoListWebApp.items SET itemChecked = ? WHERE id = ?;");

            ps.setBoolean(1, check);
            ps.setInt(2, id);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your update query is success!");
            }

            connection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ToDoListItem selectItemsById(int id){

        try {
            Statement statement = connection().createStatement();

            ResultSet resultSet =
                    statement.executeQuery("SELECT id,itemValue,itemChecked FROM ToDoListWebApp.items WHERE id = " + id + ";");
            resultSet.next();

            ToDoListItem toDoListItem =
                    new ToDoListItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3));

            connection().close();

            return toDoListItem;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
