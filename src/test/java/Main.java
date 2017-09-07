import src.DbContext.DbContextJDBC;

/**
 * Created by kkioukiou on 01.09.17.
 */
public class Main {

    public static void main(String[] args) {
        DbContextJDBC dbContextJDBC = new DbContextJDBC();
        dbContextJDBC.checkedItem(171, false);

        System.out.println(dbContextJDBC.selectItemsById(171).toString());
    }
}
