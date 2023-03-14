package se.yalar;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adressId;
    private String street;
    private int houseNumber;
    private int zipCode;
    private String city;

     @OneToMany(targetEntity = Client.class, mappedBy = "adress", cascade = CascadeType.ALL)
     private List<Client> clients;

    @OneToMany(targetEntity = Arena.class, mappedBy = "adress", cascade = CascadeType.ALL)
    private List<Arena> arenas;

    public Adress() {
    }

    public Adress(String street, int houseNumber, int zipCode, String city) {
    }

    public int getAdressId() {
        return adressId;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void setArenas(List<Arena> arenas) {
        this.arenas = arenas;
    }

    @Override
    public String toString() {
        return  "DataBasId= " + adressId + ": " +
                "Gata= '" + street + ", " +
                "Nr= " + houseNumber + ", " +
                "Postnummer= " + zipCode + ", " +
                ", Postort= '" + city + "' ";
    }
}

