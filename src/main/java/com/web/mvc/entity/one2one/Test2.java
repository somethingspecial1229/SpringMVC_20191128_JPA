package com.web.mvc.entity.one2one;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.mvc.entity.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Test2 {

    static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) throws Exception {
//        addHusbandAndWife("fff", "ssss");
//        queryHusband();
//        queryWife();
//        get(Husband.class, 701L);
//        get(Wife.class, 751L);
//        update(1101L, "Vincent", "Anita");
//        delete(1701L);
    }

    public static void addHusbandAndWife(String name1, String name2) {
        Husband husband = new Husband();
        husband.setName(name1);

        Wife wife = new Wife();
        wife.setName(name2);

        husband.setWife(wife);

        em.getTransaction().begin();
        em.persist(husband);
        em.getTransaction().commit();
        System.out.println("Add OK !");
    }
    
    public static void queryHusband() throws Exception {
        List<Husband> list = em.createQuery("Select h From Husband h", Husband.class).getResultList();
        printJSON(list);
    }
    
    public static void queryWife() throws Exception {
        List<Wife> list = em.createQuery("Select w From Wife w", Wife.class).getResultList();
        printJSON(list);
    }
    
   
    public static void get(Class cls, Long id) throws Exception {
        Object object = em.find(cls, id);
        printJSON(object);
    }
    
    public static void update(Long id, String name1, String name2) {
        Husband husband = em.find(Husband.class, id);
        if(husband == null) return;
        Wife wife = husband.getWife();
        if(name1 != null) {
            husband.setName(name1);
        }
        if(name2 != null) {
            wife.setName(name2);
        }
        em.getTransaction().begin();
        em.persist(husband);
        em.getTransaction().commit();
        System.out.println("Update OK !");
    }
     
    public static void delete(Long id) {
        Husband husband = em.find(Husband.class, id);
        if(husband == null) return;
        em.getTransaction().begin();
        em.remove(husband);
        em.getTransaction().commit();
        System.out.println("Delete OK !");
    }
    
    public static void printJSON(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        System.out.println(json);
    }
}
