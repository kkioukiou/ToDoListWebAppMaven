
import src.DbContext.Hibernate.DbContextHibernate;
import src.Models.ToDoItemXml;
import src.Models.ToDoListItem;
import src.Servlet.ToDo;

import java.util.List;

/**
 * Created by kkioukiou on 01.09.17.
 */
public class Main {

    public static void main(String[] args) {

        ToDo toDo = new ToDo();

//        toDo.insertItem("olololololol");
        toDo.deleteItem(193);
//        toDo.checkItem(ToDoItemXml.id = 174, ToDoItemXml.);
//        List<ToDoListItem> list = dbContextHibernate.selectAllItems();
//        for (ToDoListItem toDoListItem : list){
//            System.out.println(toDoListItem.getItemValue());
//        }
//        ToDoListItem toDoListItem = dbContextHibernate.selectItemsById(186);
//        System.out.println(toDoListItem.getItemValue());

//        toDo.getItems();
//
//        List<ToDoListItem> list = toDo.getItems();
//        for (ToDoListItem toDoListItem : list){
//            System.out.println(toDoListItem.getItemValue());
//        }
    }
}
