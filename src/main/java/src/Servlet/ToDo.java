package src.Servlet;

import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.sun.jersey.server.impl.model.parameter.multivalued.MultivaluedParameterExtractor;
import com.sun.jersey.server.impl.model.parameter.multivalued.MultivaluedParameterExtractorFactory;
import com.sun.org.glassfish.gmbal.ParameterNames;
import src.DbContext.DbContext;
import src.Models.ToDoItemXml;
import src.Models.ToDoListItem;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

// The Java class will be hosted at the URI path "/api/todo"
@Path("/todo")
public class ToDo {

    DbContext dbContext = new DbContext();

    @Path("/getItems")
    @GET
    @Produces("application/json")
    public List<ToDoListItem> getItems() {
        return dbContext.selectAllItems();
    }

    @Path("/insertItem")
    @POST
    @Consumes("text/plain")
    public void insertItem(String itemValue){
        dbContext.insertNewItem(itemValue);
    }

    @Path("/delete/{id}")
    @DELETE
    public void deleteItem(@PathParam("id") int id) {
        dbContext.deleteItem(id);
    }

    @Path("/checkItem")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void checkItem(ToDoItemXml toDoItemXml){
        System.out.println(toDoItemXml.id + " " + toDoItemXml.check);
        dbContext.checkedItem(toDoItemXml.id, toDoItemXml.check);
    }
}