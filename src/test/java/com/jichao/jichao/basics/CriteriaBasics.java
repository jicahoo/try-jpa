package com.jichao.jichao.basics;

import com.jichao.jpa.entity.Point;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by jichao on 2016/11/11.
 */
public class CriteriaBasics {

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
        TypedQuery<Tuple> typedQueryTuple = em.createQuery("SELECT AVG(p.x) as avgval FROM Point p", Tuple.class);
        Tuple tupleResult = typedQueryTuple.getSingleResult();
        System.out.println("Average X: " + tupleResult.get("avgval"));

        // Retrieve all the Point objects from the database:
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query= criteriaBuilder.createTupleQuery();

        Root<Point> pointRoot = query.from(Point.class);
        query.multiselect(pointRoot.get("x").alias("xx"));

        List<Tuple> list = em.createQuery(query).getResultList();

        for (Tuple obj : list) {
            System.out.println(obj.get("xx"));
        }

        TypedQuery<Tuple> l = em.createQuery("select p.x as xx from Point p", Tuple.class);
        list = l.getResultList();
        for (Tuple obj : list) {
            System.out.println(obj.get("xx"));
        }

        // Close the database connection:
        em.close();
        emf.close();
    }

}
