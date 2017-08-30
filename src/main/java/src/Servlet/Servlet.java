package src.Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import src.DbContext.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet") //api/todo
public class Servlet extends HttpServlet {

    private DbContext dbContext = new DbContext();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonPrimitive jsonPrimitive = new Gson().fromJson(req.getReader(), JsonPrimitive.class);

//        ToDoListItem tdli = new ToDoListItem(obj.get("id").getAsInt(), obj.get("value").getAsString());

        dbContext.insertNewItem(jsonPrimitive.getAsString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(dbContext.selectAllItems());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject obj = new Gson().fromJson(req.getReader(), JsonObject.class);
        int id = obj.get("id").getAsInt();
        dbContext.deleteItem(id);
    }

}