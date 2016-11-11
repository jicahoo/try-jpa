package com.jichao.jichao.basics;

import com.jichao.jpa.entity.Customer;
import com.jichao.jpa.entity.HomeAddress;
import com.jichao.jpa.entity.Point;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jichao on 2016/11/10.
 */
public class JpaBasics {

    @Test
    public void testCrud() {
        //http://www.objectdb.com/tutorial/jpa/start
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
                em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }

    @Test
    public void testAlias() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("points.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        Query query =
                em.createQuery("SELECT p.x as x FROM Point p");
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj.getClass());

            System.out.println(obj);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }

    @Test
    public void testOneToOne() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("customers.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Customer p = new Customer();
            p.setName("Focus");
            HomeAddress homeAddress = new HomeAddress();
            homeAddress.setCountry("China");
            p.setHomeAddress(homeAddress);
            em.persist(p);
        }
        em.getTransaction().commit();

        TypedQuery<Customer> query =
                em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> list = query.getResultList();
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }


}
