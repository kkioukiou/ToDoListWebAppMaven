package src.DbContext;

import src.Models.ToDoListItem;

import java.util.List;

public interface DbContext {

    void insertNewItem(String toDoListItem);

    void deleteItem(int id);

    List<ToDoListItem> selectAllItems();

    void checkedItem(int id, boolean check);

    ToDoListItem selectItemsById(int id);

}
