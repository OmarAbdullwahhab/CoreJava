/*
 * The MIT License
 *
 * Copyright 2021 Omar AbdullWahhab
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package extreme.tutors.jpaprogramming;


import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Omar AbdullWahhab
 * @date : 30-07-2021 12:00 AM
 */
public class JavaDatabaseProgrammingJpa {

    static String unitName = "TutorPu";
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        listAll(em);
        em.clear();
        em.close();
        emf.close();

    }

    static void listAll(EntityManager em) {
        Query query = em.createQuery("select p from Person p");
        List persons = query.getResultList();
        for (Object p : persons) {
            Person psn = (Person) p;
            System.out.println(psn.toString());
        }
    }

    static void create(EntityManager em) {
        Person p = new Person();
        p.setId(10);
        p.setFirstName("AAMER");
        p.setLastName("ALI");
        p.setDateOfBirth(Date.valueOf(LocalDate.of(2020, Month.MARCH, 25)));

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();

    }

    static void update(EntityManager em, int personId) {
        em.getTransaction().begin();
        Person p = em.find(Person.class, personId);
        p.setFirstName("Maher");
        p.setLastName("Hamad");
        p.setDateOfBirth(Date.valueOf(LocalDate.of(2005, Month.DECEMBER, 25)));
        em.persist(p);
        em.getTransaction().commit();
    }

    static void delete(EntityManager em, int personId) {
        em.getTransaction().begin();
        Person p = em.find(Person.class, personId);
        em.remove(p);
        em.getTransaction().commit();
    }
}
