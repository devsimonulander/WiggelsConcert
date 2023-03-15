package se.yalar;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

public class CRUD {

    // Arena och Concert har vanliga "id" istället för arenaId osv.
    private final SessionFactory sessionFactory;

    public CRUD(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Client> getAllClient() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
            criteria.from(Client.class);
            return session.createQuery(criteria).getResultList();
        }
    }

    public Client getClientById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        }
    }

    public void addClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }
    }

    public void updateClient(int clientId, String firstName, String lastName, String phoneNumber ,String street, int houseNumber, int zipCode, String city ) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setPhoneNumber(phoneNumber);
            int adId = client.getAdress().getAdressId();
            updateAdress(adId, street, houseNumber, zipCode, city);
            session.update(client);
            session.getTransaction().commit();
        }
    }

    public void deleteClient(int clientId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            session.delete(client);
            session.getTransaction().commit();
        }
    }

    //************************************

    public List<Concert> getAllConcerts() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Concert> criteria = builder.createQuery(Concert.class);
            criteria.from(Concert.class);
            return session.createQuery(criteria).getResultList();
        }
    }

    public Concert getConcertById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Concert.class, id);
        }
    }

    public void addConcert(Concert concert) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(concert);
            session.getTransaction().commit();
        }
    }

    public void updateConcert(int concertId, String artist_name, Date date, int price, int age_limit, String arena) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Concert concert = session.get(Concert.class, concertId);
            concert.setArtist_name(artist_name);
            concert.setDate(date);
            concert.setPrice(price);
            concert.setAge_limit(age_limit);
            session.update(concert);
            session.getTransaction().commit();
        }
    }

    public void deleteConcert(int concertId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Concert concert = session.get(Concert.class, concertId);
            session.delete(concert);
            session.getTransaction().commit();
        }
    }

    //************************************

    public List<Adress> getAllAdresses() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Adress> criteria = builder.createQuery(Adress.class);
            criteria.from(Adress.class);
            return session.createQuery(criteria).getResultList();
        }
    }

    public Adress getAdressById(int adressId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Adress.class, adressId);
        }
    }

    public void addAdress(Adress adress) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(adress);
            session.getTransaction().commit();
        }
    }
     // updateFunkarAdress

    public void updateAdress(int adressId, String street, int houseNumber, int zipCode, String city) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Adress adress = session.get(Adress.class, adressId);
            adress.setStreet(street);
            adress.setHouseNumber(houseNumber);
            adress.setZipCode(zipCode);
            adress.setCity(city);
            session.update(adress);
            session.getTransaction().commit();
        }
    }

    public void deleteAdress(int adressId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Adress adress = session.get(Adress.class, adressId);
            session.delete(adress);
            session.getTransaction().commit();
        }
    }

    //**********************************

    public List<Arena> getAllArenas() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Arena> criteria = builder.createQuery(Arena.class);
            criteria.from(Arena.class);
            return session.createQuery(criteria).getResultList();
        }
    }

    public Arena getArenaById(int id) { // arenaId?
        try (Session session = sessionFactory.openSession()) {
            return session.get(Arena.class, id);
        }
    }

    public void addArena(Arena arena) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(arena);
            session.getTransaction().commit();
        }
    }

    public void updateArena(int id, String name, String type,String street, int houseNumber, int zipCode, String city) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Arena arena = session.get(Arena.class, id);
            arena.setName(name);
            int adId = arena.getAdress().getAdressId();
            updateAdress(adId, street, houseNumber, zipCode, city);
            arena.setType(type);
            session.update(arena);
            session.getTransaction().commit();
        }
    }

    public void deleteArena(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Arena arena = session.get(Arena.class, id);
            session.delete(arena);
            session.getTransaction().commit();
        }
    }
}
