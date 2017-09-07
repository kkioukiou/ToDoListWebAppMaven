package src.Servlet;

import src.DbContext.DbContextJDBC;
import src.Models.ToDoItemXml;
import src.Models.ToDoListItem;
import javax.ws.rs.*;
import java.util.List;

// The Java class will be hosted at the URI path "/api/todo"
@Path("/todo")
public class ToDo {

    DbContextJDBC dbContextJDBC = new DbContextJDBC();

    @Path("/Items")
    @GET
    @Produces("application/json")
    public List<ToDoListItem> getItems() {
        return dbContextJDBC.selectAllItems();
    }

    @Path("/Item")
    @POST
    @Consumes("text/plain")
    public void insertItem(String itemValue){
        dbContextJDBC.insertNewItem(itemValue);
    }

    @Path("/Item/{id}")
    @DELETE
    public void deleteItem(@PathParam("id") int id) {
        dbContextJDBC.deleteItem(id);
    }

    @Path("/Item")
    @PUT
    @Consumes("application/json")
    public void checkItem(ToDoItemXml toDoItemXml){
        dbContextJDBC.checkedItem(toDoItemXml.id, toDoItemXml.check);
    }
}