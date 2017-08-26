package src.Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import src.Models.ToDoListItem;
import src.DbContext.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/WorkServlet") //api/todo
public class WorkServlet extends HttpServlet {

    private DbContext dbContext = new DbContext();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonPrimitive jsonPrimitive = new Gson().fromJson(req.getReader(), JsonPrimitive.class);

//        ToDoListItem tdli = new ToDoListItem(obj.get("id").getAsInt(), obj.get("value").getAsString());

        dbContext.insertNewItem(jsonPrimitive.getAsString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ToDoListItem> list = new ArrayList<ToDoListItem>();
        ResultSet rs = dbContext.selectAllItems();

        try {
            while (rs.next()){
                list.add(new ToDoListItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(list);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject obj = new Gson().fromJson(req.getReader(), JsonObject.class);
        int id = obj.get("id").getAsInt();
        dbContext.deleteItem(id);
    }

}