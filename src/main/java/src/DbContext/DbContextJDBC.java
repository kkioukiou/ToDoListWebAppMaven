package src.DbContext;

import src.Models.ToDoListItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbContextJDBC implements DbContext {

    public void insertNewItem(String toDoListItem){

        Connection connection = null;

        try {
            connection = DbConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT ToDoListWebApp.items (itemValue) VALUES (?)");

            ps.setString(1, toDoListItem);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your insert query is success!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteItem(int id){

        Connection connection = null;

        try {
            connection = DbConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("DELETE FROM ToDoListWebApp.items WHERE id=?");

            ps.setInt(1, id);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your delete query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ToDoListItem> selectAllItems(){

        List<ToDoListItem> list = new ArrayList<ToDoListItem>();
        Connection connection = null;

        try {
            connection = DbConnectJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id,itemValue,itemChecked FROM ToDoListWebApp.items;");

            while (resultSet.next()){
                list.add(new ToDoListItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void checkedItem(int id, boolean check){

        Connection connection = null;
        try {
            connection = DbConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("UPDATE ToDoListWebApp.items SET itemChecked = ? WHERE id = ?;");

            ps.setBoolean(1, check);
            ps.setInt(2, id);

            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("Your update query is success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ToDoListItem selectItemsById(int id){

        ToDoListItem toDoListItem = new ToDoListItem(0, "0", false);
        Connection connection = null;
        try {
            connection = DbConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT id,itemValue,itemChecked FROM ToDoListWebApp.items WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            toDoListItem = new ToDoListItem
                    (resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3));

            return toDoListItem;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return toDoListItem;
    }
}
