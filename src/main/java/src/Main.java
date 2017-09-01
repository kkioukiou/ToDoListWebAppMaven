package src;

import src.DbContext.DbContext;

/**
 * Created by kkioukiou on 01.09.17.
 */
public class Main {

    public static void main(String[] args) {
        DbContext dbContext = new DbContext();
        dbContext.checkedItem(147, true);

        System.out.println(dbContext.selectItemsById(147).toString());
    }
}
