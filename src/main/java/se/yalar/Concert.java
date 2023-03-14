package se.yalar;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String artist_name;
    private Date date;
    private int price;
    private int age_limit;
    private String arena;
    @ManyToMany
    @JoinTable(name = "WC",
    joinColumns = {@JoinColumn(name = "ConcertId")},
    inverseJoinColumns = {@JoinColumn(name = "ClientId")})
    private List<Client> clients = new ArrayList<>();

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", artist_name='" + artist_name + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", age_limit=" + age_limit +
                ", arena='" + arena + '\'' +
                '}';
    }
}
