package src.WorkPackage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import src.WorkWithDatabase.WorkWithDataBase;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkClass {

    private WorkWithDataBase workWithDataBase = new WorkWithDataBase();

    public void metod(HttpServletRequest req) throws IOException {

        JsonObject obj = new Gson().fromJson(req.getReader(), JsonObject.class);

        ToDoListItem tdli = new ToDoListItem(obj.get("id").getAsInt(), obj.get("value").getAsString());

        workWithDataBase.insertNewItemToDataBase(tdli);

    }

    public String startMethod(){
        List<ToDoListItem> list = new ArrayList<ToDoListItem>();
        ResultSet rs = workWithDataBase.selectAllItemsFromDataBase();

        try {
            while (rs.next()){
                list.add(new ToDoListItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(list);
        System.out.println(list);
        return json;
    }

    public void deleteItem(HttpServletRequest req) throws IOException {
        JsonObject obj = new Gson().fromJson(req.getReader(), JsonObject.class);

        ToDoListItem toDoListItem =
                new ToDoListItem
                        (obj.get("id").getAsInt(), obj.get("value").getAsString());

        workWithDataBase.deleteItemFromDataBase(toDoListItem);
    }
}
