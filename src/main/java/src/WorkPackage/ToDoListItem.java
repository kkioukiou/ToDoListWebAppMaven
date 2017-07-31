package src.WorkPackage;

/**
 * Created by kkioukiou on 16.07.17.
 */
public class ToDoListItem {

    private int id;
    private String value;

    public ToDoListItem(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.value;
    }
}
