package se.yalar;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concertId;
    private String artistName;
    private Date date;
    private int price;
    private int age_limit;

    @ManyToOne
    @JoinColumn(name = "arenaId")
    private Arena arena;

    public Concert() {
    }

    public String getArtist_name() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
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
                ", artist namn: ='" + artistName + '\'' +
                ", datum " + date +
                ", pris " + price +
                ", åldersgräns: " + age_limit +
                ", arena='" + arena + '\'' +
                '}';
    }
}
