package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) { //автоматически закроет когда мы закончим работу
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Actor actor = session.get(Actor.class,2);
            System.out.println(actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(0); // для remove реализовали hashcode and equals во всех двух классах
            movieToRemove.getActors().remove(actor); // для remove реализовали hashcode and equals во всех двух классах

            session.getTransaction().commit();

        }
    }
}
