package se.yalar;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "adressId")
    private Adress adress;
    @ManyToMany
    @JoinTable(name = "WC",
    joinColumns = {@JoinColumn(name = "ClientId")},
    inverseJoinColumns = {@JoinColumn(name = "ConcertId")})
    private List<Concert>concerts = new ArrayList<>();


    public Client(){}
    public Client(String firstName, String lastName, String date, String phoneNumber, Adress adress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = Date.valueOf(date);
        this.phoneNumber = phoneNumber;
        this.adress = adress;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String date) {
        Date birthday = Date.valueOf(date);
        this.birthday = birthday;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}
