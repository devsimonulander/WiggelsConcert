package se.yalar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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


        Concert concert1 = new Concert("The Strokes","02/07/2023",900,arena,18);
        Concert concert2 = new Concert("The Clash","05/09/2023",1200,arena,21);
        Concert concert3 = new Concert("Pink Floyd","07/04/2023",1800,arena,21);
        Concert concert4 = new Concert("The White Stripes","03/06/2024",900,arena,18);
        Concert concert5 = new Concert("Babblarna","08/07/2024",9999,arena,3);

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

      // wc.updateClient(3, "Ida", "Karlsson", "035215886", a2); // Funkar ej
        CRUD crud = new CRUD(sessionFactory);
/*        List<Adress> cons = crud.getAllAdresses();
        for(int i = 0; i < cons.size(); i++){
            System.out.println(cons.get(i).getStreet());
        }
        crud.updateAdress(6, "Husvägen", 3456, 89046, "Sverige");*/
        //crud.updateClient(5, "Peter", "Kil", "046598723", "Koklvägen", 56874, 5223, "Berg");
        crud.updateArena(1, "Ullevi", "Ute", "Göteborgsvägen", 46792, 850, "Göteborg");
    /*    Concert co = crud.getConcertById(4);
        System.out.println(co.getArtist_name());


        Concert c1 = new Concert();
        c1.setArtist_name("jgkln");

        crud.addConcert(c1);
        crud.deleteConcert(4);*/
/*
        Client c1 = new Client();
        Client c2 = new Client();
        Client c3 = new Client();
        Client c4 = new Client();
        Client c5 = new Client();
        c1.setFirstName("Anna");
        c2.setFirstName("Pelle");
        c3.setFirstName("Kajsa");
        c4.setFirstName("Olle");
        c5.setFirstName("Sofia");

        Concert concert = new Concert();
        Concert concert1 = new Concert();
        Concert concert2 = new Concert();
        concert.setArtist_name("hfjs");
        concert1.setArtist_name("Plfji");
        concert2.setArtist_name("Ssjik");*/
/*
        concert.getClients().add(c1);
        concert.getClients().add(c2);
        concert.getClients().add(c3);

        concert1.getClients().add(c4);
        concert1.getClients().add(c5);*/
/*        concert2.getClients().add(c2);
        concert2.getClients().add(c5);
        concert2.getClients().add(c3);*/

/*        session.persist(c1);
        session.persist(c2);
        session.persist(c3);
        session.persist(c4);
        session.persist(c5);
        session.persist(concert);
        session.persist(concert1);*/
       /* session.persist(concert2);*/
/*


        Query query = session.createQuery("FROM Concert");
        List<Concert> result = query.list();
        for(Concert o : result){
            System.out.println(o.getArtist_name() + " har följande kunder");
            for(int i = 0; i < o.getClients().size(); i++){
                System.out.println(o.getClients().get(i).getFirstName());
            }
        }*/

        session.getTransaction().commit();
        session.close();
    }
}