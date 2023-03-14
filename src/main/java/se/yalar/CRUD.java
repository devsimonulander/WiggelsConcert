package se.yalar;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CRUD {
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

    public void updateClient(int clientId, String firstName, String lastName, String phoneNumber, Adress adress) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setPhoneNumber(phoneNumber);
            client.setAdress(adress);
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
}
