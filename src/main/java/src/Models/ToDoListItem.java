package src.Models;

/**
 * Created by kkioukiou on 16.07.17.
 */
public class ToDoListItem {

    private int id;
    private String value;
    private boolean check;

    public ToDoListItem(int id, String value, boolean check) {
        this.id = id;
        this.value = value;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public boolean isCheck() {
        return check;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.value + " - " + this.check;
    }
}
