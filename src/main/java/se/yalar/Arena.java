package se.yalar;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int arenaId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "adressId")
    private Adress adress;
    private String type; // Ex: inside/outside/etc...
    @OneToMany(targetEntity = Concert.class, mappedBy = "concert", cascade = CascadeType.ALL)
    private List<Concert> concerts;

    public Arena() {

    }

    public int getId() {
        return arenaId;
    }

    public void setId(int Arenaid) {
        this.arenaId = arenaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public List<Concert> getConcert() {
        return concerts;
    }

    public void setConcert(List<Concert> concerts) {
        this.concerts = concerts;
    }
}