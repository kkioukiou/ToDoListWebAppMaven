package src.Models;

import javax.persistence.*;

/**
 * Created by kkioukiou on 08.09.17.
 */
@Entity
@Table(name = "items", schema = "ToDoListWebApp")
public class ToDoListItem {
    private int id;
    private String itemValue;
    private boolean itemChecked;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "itemValue", length = 100)
    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    @Basic
    @Column(name = "itemChecked")
    public boolean getItemChecked() {
        return itemChecked;
    }

    public void setItemChecked(boolean itemChecked) {
        this.itemChecked = itemChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoListItem that = (ToDoListItem) o;

        if (id != that.id) return false;
        if (itemChecked != that.itemChecked) return false;
        if (itemValue != null ? !itemValue.equals(that.itemValue) : that.itemValue != null) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (itemValue != null ? itemValue.hashCode() : 0);
//        result = 31 * result + (byte) itemChecked;
//        return result;
//    }
}
