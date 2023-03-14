package se.yalar;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concertId;
    private String artist_name;
    private Date date;
    private int price;
    private int age_limit;
    private int arenaId;

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

    public int getArena() {
        return arenaId;
    }

    public void setArena(int arenaId) {
        this.arenaId = arenaId;
    }

    public void setConcertId(int id) {
        this.concertId = id;
    }
    public int getConcertId() {
        return concertId;
    }
    @Override
    public String toString() {
        return "Concert{" +
                "id=" + concertId +
                ", artist namn: ='" + artist_name + '\'' +
                ", datum " + date +
                ", pris " + price +
                ", åldersgräns: " + age_limit +
                ", arena='" + arenaId + '\'' +
                '}';
    }
}
