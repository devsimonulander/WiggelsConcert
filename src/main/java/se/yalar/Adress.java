package se.yalar;

import jakarta.persistence.*;

@Entity
    public class Adress {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int adressId;
        private String street;
        private int houseNumber;
        private int zipCode;
        private String city;

       // @ManyToOne
        //private Client client; behövs ett sånt attribut

        //@ManyToOne
      //  private Arena arena;

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

        @Override
        public String toString() {
            return  "DataBasId= " + adressId + ": " +
                    "Gata= '" + street + ", " +
                    "Nr= " + houseNumber + ", " +
                    "Postnummer= " + zipCode + ", " +
                    ", Postort= '" + city + '\'';
        }
    }
