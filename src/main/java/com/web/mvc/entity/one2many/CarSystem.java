package com.web.mvc.entity.one2many;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.mvc.entity.JPAUtil;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CarSystem {
    static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    static ObjectMapper obj = new ObjectMapper();
    
    public static void menu()throws Exception {
        System.out.println("-----------------");
        System.out.println("1. 新增 Driver");
        System.out.println("2. 新增 Car");
        System.out.println("3. 查詢 Drivers");
        System.out.println("4. 查詢 Cars");
        System.out.println("5. 單查 Driver");
        System.out.println("0. 離開 Exit");
        System.out.println("-----------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入選項: ");
        switch(sc.next()) {
            case "1":
                System.out.println("請輸入 Driver 名稱: ");
                addDriver(sc.next());
                break;
            case "2":
                System.out.println("請輸入 Car 名稱: ");
                addCar(sc.next());
                break;
            case "3":
                queryDrivers();
                break;
            case "4":
                queryCars();
                break;
            case "5":
                System.out.println("請輸入要查詢的 Driver 名稱: ");
                Object driver = getDriver(sc.next());
                if (driver != null) {
                    System.out.println(obj.writeValueAsString(driver));
                }
                break;
            case "0":    
                return;
        }
        menu();
    }
    
    public static void addDriver(String name) {
        Driver driver = new Driver();
        driver.setName(name);
        em.getTransaction().begin();
        em.persist(driver);
        em.getTransaction().commit();
        System.out.println("Driver 新增成功 !");
    }
    
    public static void addCar(String name) {
        Car car = new Car();
        car.setName(name);
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        System.out.println("Car 新增成功 !");
    }
    
    public static void queryDrivers()throws Exception {
        em.clear();
        List<Driver> drivers = em.createQuery("Select d From Driver d").getResultList();
        System.out.println(obj.writeValueAsString(drivers));
    }
    
    public static void queryCars()throws Exception {
        em.clear();
        List<Car> cars = em.createQuery("Select c From Car c").getResultList();
        System.out.println(obj.writeValueAsString(cars));
    }
    
    public static Object getDriver(String name){
        Query q = em.createQuery("Select d From Driver d Where d.name =:name");
        q.setParameter("name", name);
        int size = q.getResultList().size();
        if(size == 0) {
            System.out.println("查無此人");
            return null;
        }
        Driver driver = (Driver)q.getSingleResult();
        return driver;
    }
    
    public static void main(String[] args)throws Exception {
        menu();
    }
}
