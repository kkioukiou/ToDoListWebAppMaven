package src.DbContext.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import src.DbContext.DbContext;
import src.Models.ToDoListItem;

import java.util.List;

public class DbContextHibernate implements DbContext {

    DbConnectHibernate dbConnectHibernate = new DbConnectHibernate();

    private SessionFactory sessionFactory;

    protected void setup() {
        // code to load Hibernate Session factory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(ex);
        }
    }

    protected void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }

    public void insertNewItem(String str) {
        // code to save a toDoListItem
        ToDoListItem toDoListItem = new ToDoListItem();
        setup();

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            toDoListItem.setItemValue(str);
            session.save(toDoListItem);
            tx.commit();
        } catch (Exception e){
            if (tx != null) tx.rollback();
//            throw e;
        } finally {
            session.close();
        }
        exit();
    }

    public void deleteItem(int id) {
        // code to remove a item
        ToDoListItem toDoListItem = new ToDoListItem();

        setup();

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            toDoListItem.setId(id);
            session.delete(toDoListItem);
            tx.commit();
        } catch (Exception e){
            if (tx != null) tx.rollback();
//            throw e;
        } finally {
            session.close();
        }

        exit();
    }

    public List<ToDoListItem> selectAllItems() {
        //How refactor this code to try/catch?
        setup();

        Session session = sessionFactory.openSession();
        String hql = "from ToDoListItem";
        Query query = session.createQuery(hql);
        List<ToDoListItem> list = query.list();
        session.close();

        exit();
        return list;
    }

    public void checkedItem(int id, boolean check) {
        // code to modify a item
        ToDoListItem toDoListItem = new ToDoListItem();

        setup();

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.load(toDoListItem, id);
            toDoListItem.setItemChecked(check);
            session.update(toDoListItem);
            tx.commit();
        } catch (Exception e){
            if (tx != null) tx.rollback();
//            throw e;
        } finally {
            session.close();
        }
        exit();
    }

    public ToDoListItem selectItemsById(int id) {
        // code to get a item
        ToDoListItem toDoListItem = new ToDoListItem();

        setup();

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.load(toDoListItem, id);
            tx.commit();
        } catch (Exception e){
            if (tx != null) tx.rollback();
//            throw e;
        } finally {
            session.close();
        }

        exit();

        return toDoListItem;
    }

}
