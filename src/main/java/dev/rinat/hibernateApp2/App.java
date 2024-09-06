package dev.rinat.hibernateApp2;

import dev.rinat.hibernateApp2.model.Item;
import dev.rinat.hibernateApp2.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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
//            Person person = currentSession.get(Person.class, 4);
//            Item item = currentSession.get(Item.class, 1);
//            item.getOwner().getItems().remove(item);
//            item.setOwner(person);
//            person.getItems().add(item);


//            test cascading
//            Person person = new Person("test_cascading", 33);
//            Item item = new Item("test_cascading_item", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(item)));
//            currentSession.persist(person);


//            simplify test cascading
//            Person person = new Person("test_cascading", 33);
//            person.addItem(new Item("Item1"));
//            person.addItem(new Item("Item2"));
//            person.addItem(new Item("Item3"));
//            currentSession.persist(person);


//            check eager loading
//            Item item = currentSession.get(Item.class, 1);
//            System.out.println("got item: " + item);
//            System.out.println("owner: " + item.getOwner());


//            check lazy loading
//            Person person = currentSession.get(Person.class, 1);
//            System.out.println("got person: " + person);
//            System.out.println("items: " + person.getItems());

//            for loading binding entities:
//            Hibernate.initialize(person.getItems());

//            currentSession.getTransaction().commit();
//            get items after session when eager loading
//            System.out.println("items: " + person.getItems());


//            initialize after session closed
//            Person person = currentSession.get(Person.class, 1);
//            System.out.println("got person: " + person);
//            currentSession.getTransaction().commit();
//            System.out.println("session closed");
//
//            currentSession = sessionFactory.getCurrentSession();
//            currentSession.beginTransaction();
//            System.out.println("inside second transaction");
//
//            person = currentSession.merge(person);
//            Hibernate.initialize(person.getItems());
//
//            currentSession.getTransaction().commit();
//
//            System.out.println("after second session closed");
//            System.out.println("items: " + person.getItems());


//            another method to initialize using HQL query
            Person person = currentSession.get(Person.class, 1);
            System.out.println("got person: " + person);
            currentSession.getTransaction().commit();
            System.out.println("session closed");

            currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            System.out.println("inside second transaction");

            List<Item> items =
                    currentSession.createQuery("select i from Item i where i.owner.id = :personId", Item.class)
                            .setParameter("personId", person.getId()).getResultList();
            System.out.println("items: " + items);

            currentSession.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
