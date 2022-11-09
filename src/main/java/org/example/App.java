package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;




public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Test cascading",35);
            person.addItem(new Item("item1"));
            person.addItem(new Item("item2"));
            person.addItem(new Item("item3"));

            session.save(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
