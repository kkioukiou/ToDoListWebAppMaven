package src.Servlet;

import src.DbContext.Hibernate.DbContextHibernate;
import src.Models.ToDoItemXml;
import src.Models.ToDoListItem;

import javax.ws.rs.*;
import java.util.List;

// The Java class will be hosted at the URI path "/api/todo"
@Path("/todo")
public class ToDo {

    DbContextHibernate dbContextHibernate = new DbContextHibernate();

    @Path("/Items")
    @GET
    @Produces("application/json")
    public List<ToDoListItem> getItems() {
        return dbContextHibernate.selectAllItems();
    }

    @Path("/Item")
    @POST
    @Consumes("text/plain")
    public void insertItem(String itemValue){
        dbContextHibernate.insertNewItem(itemValue);
    }

    @Path("/Item/{id}")
    @DELETE
    public void deleteItem(@PathParam("id") int id) {
        dbContextHibernate.deleteItem(id);
    }

    @Path("/Item")
    @PUT
    @Consumes("application/json")
    public void checkItem(ToDoItemXml toDoItemXml){
        dbContextHibernate.checkedItem(toDoItemXml.id, toDoItemXml.check);
    }
}