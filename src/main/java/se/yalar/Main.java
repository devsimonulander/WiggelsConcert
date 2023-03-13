package se.yalar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
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