package se.yalar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gameloop gameloop = new Gameloop();
        gameloop.start();
        System.out.println("Funkaaaa");
        System.out.println("Hej hej");



        Adress adress1 = new Adress("Paradisäppelvägen", 111, 98765, "Ankeborg");
        Adress adress2 = new Adress("Teatergatan", 18, 11122, "Valleby");
        Adress adress3 = new Adress("Privet Drive", 4, 88888, "Little Whinging");
        Adress adress4 = new Adress("Bedford Street", 90, 44444, "New York");
        Adress adress5 = new Adress("Malibu Point", 10880, 90265, "Malibu");



        Client client = new Client();
        client.setFirstName("Saga");
        Adress a1 = new Adress("Husvägen", 456, 2348, "Sverige");
        Adress a2 = new Adress("Kungsgatan", 65546, 2348, "Sverige");
        Arena arena = new Arena();
        arena.setName("Globen");

        Concert concert1 = new Concert();
        concert1.setArtistName("The Strokes");
        Date date1 = new Date(10/07/2023);
        concert1.setDate(date1);
        concert1.setPrice(900);
        concert1.setArena(arena);
        concert1.setAge_limit(18);

        Concert concert2 = new Concert();
        concert2.setArtistName("The Clash");
        Date date2 = new Date(05/11/2023);
        concert2.setDate(date2);
        concert2.setPrice(700);
        concert2.setArena(arena);
        concert2.setAge_limit(21);

        Concert concert3 = new Concert();
        concert3.setArtistName("Pink Floyd");
        Date date3 = new Date(04/06/2024);
        concert3.setDate(date3);
        concert3.setPrice(1200);
        concert3.setArena(arena);
        concert3.setAge_limit(18);

        Concert concert4 = new Concert();
        concert4.setArtistName("The White Stripes");
        Date date4 = new Date(02/01/2024);
        concert4.setDate(date4);
        concert4.setPrice(1350);
        concert4.setArena(arena);
        concert4.setAge_limit(21);

        Concert concert5 = new Concert();
        concert5.setArtistName("Babblarna");
        Date date5 = new Date(05/05/2023);
        concert5.setDate(date5);
        concert5.setPrice(9999);
        concert5.setArena(arena);
        concert5.setAge_limit(3);

        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        client.setAdress(a1);

        session.persist(a1);
        session.persist(client);

        arena.setAdress(a2);

        session.persist(a2);
        session.persist(arena);


/*      String dataBase = "CREATE DATABASE IF NOT EXISTS groupTask";

        // Skapa en adresstabell i databasen genom SQL
        String tableAdress = "CREATE TABLE IF NOT EXISTS adress (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "street VARCHAR (45), " +
                "houseNumber int, " +
                "zipCode int, " +
                "city VARCHAR(45)) ;";
        session.createNativeQuery(tableAdress).executeUpdate();

        String insertData = "INSERT INTO adress (street, houseNumber, zipCode, city) VALUES " +
                "('Paradisäppelvägen', 111, 98765, 'Ankeborg'), " +
                "('Teatergatan', 18, 11122, 'Valleby'), " +
                "('PrivetDrive', 4, 88888, 'LittleWhinging'), " +
                "('BedfordStreet', 90, 44444, 'NewYork'), " +
                "('MalibuPoint', 10880, 90265, 'Malibu')";
        session.createNativeQuery(insertData).executeUpdate();

        Query query = session.createQuery("FROM Adress");
        List result = query.list();
        for (Object o : result) {
            System.out.println(o.toString());
        }*/

        session.getTransaction().commit();
        session.close();
    }
}