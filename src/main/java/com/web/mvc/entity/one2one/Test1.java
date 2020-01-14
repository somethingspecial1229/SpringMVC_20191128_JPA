package com.web.mvc.entity.one2one;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.mvc.entity.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;

public class Test1 {

    static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) throws Exception {
        //add("老王");
        //get(1L);
        //query();
        //update(1L,"小美");
        delete(1L);
    }

    public static void add(String name) {
        Person person = new Person();
        person.setName(name);
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        System.out.println("Add OK !");
    }

    public static void get(Long id) throws Exception {
        Person person = em.find(Person.class, id);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }

    public static void query() throws Exception {
        List<Person> list = em.createQuery("Select p From Person p", Person.class).getResultList();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
    }

    public static void update(Long id, String name) throws Exception {
        Person person = em.find(Person.class, id);
        if (person != null) {
            person.setName(name);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            System.out.println("update OK !");
        } else {
            System.out.println("查無此資料");
        }
    }
    
    public static void delete(Long id) throws Exception {
        Person person = em.find(Person.class, id);
        if (person != null) {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            System.out.println("update OK !");
        } else {
            System.out.println("查無此資料");
        }
    }

}
