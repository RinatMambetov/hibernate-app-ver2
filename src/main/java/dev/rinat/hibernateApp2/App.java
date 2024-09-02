package dev.rinat.hibernateApp2;

import dev.rinat.hibernateApp2.model.Item;
import dev.rinat.hibernateApp2.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration =
                new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();

        try {
            currentSession.beginTransaction();
//            view item from person
//            Person person1 = currentSession.get(Person.class, 1);
//            System.out.println(person1);
//            List<Item> items1 = person1.getItems();
//            System.out.println(items1);

//            view person from item
//            Item item1 = currentSession.get(Item.class, 5);
//            System.out.println(item1);
//            Person owner1 = item1.getOwner();
//            System.out.println(owner1);

//            new item
//            Person person2 = currentSession.get(Person.class, 2);
//            Item testItem = new Item("test_item", person2);
//            person2.getItems().add(testItem);
//            currentSession.persist(testItem);

//            new person and item
//            Person person3 = new Person("test_person", 33);
//            Item testItem2 = new Item("test_item2", person3);
//            person3.setItems(new ArrayList<>(Collections.singletonList(testItem2)));
//            testItem2.setOwner(person3);
//            currentSession.persist(person3);
//            currentSession.persist(testItem2);

//            remove items
//            Person person3 = currentSession.get(Person.class, 3);
//            List<Item> items = person3.getItems();
//            for (Item item : items) {
//                currentSession.remove(item);
//            }
//            person3.getItems().clear();

//            remove person
//            Person person = currentSession.get(Person.class, 2);
//            currentSession.remove(person);
//            person.getItems().forEach(i -> i.setOwner(null));

//            change owner
            Person person = currentSession.get(Person.class, 4);
            Item item = currentSession.get(Item.class, 1);
            item.getOwner().getItems().remove(item);
            item.setOwner(person);
            person.getItems().add(item);

            currentSession.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
