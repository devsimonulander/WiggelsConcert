package se.yalar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Gameloop {
    private Scanner scan;
    private int validInput[];
    private CRUD crud;
    private Client c;
    private boolean run = true;

    public Gameloop(){
        scan = new Scanner(System.in);
        validInput = new int[]{1,2,3,4};
    }

    public boolean hasOnlyDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String stringInput(String message){
        System.out.println(message);
        System.out.print("> ");
        String input = scan.nextLine();
        return input;
    }
    public int intInput(String message) {
        //boolean isDone = false;
        System.out.println(message);
        System.out.print("> ");
        return scan.nextInt();

        /*while (!isDone) {

            if (hasOnlyDigits(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Invalid input!");*/

    }

    public void showMenu(){
        System.out.println("-------------------------");
        System.out.println("|   Wigell Concerts     |");
        System.out.println("|  Concerts and events  |");
        System.out.println("-------------------------");
        System.out.println("");

        System.out.println("Menu:");
        System.out.println("1. Aktuella konserter");
        System.out.println("2. Köpa biljett");
        System.out.println("3. Ändra användaruppgifter");
        System.out.println("4. Avboka biljett");
        System.out.println("5. Avsluta");

    }

    public void action(int action, SessionFactory sessionFactory){
        switch(action){
            case 1:
                crud = new CRUD(sessionFactory);
                List<Concert> conserts = crud.getAllConcerts();
                for(int i = 0; i < conserts.size(); i++){
                    System.out.println(conserts.get(i).toString());
                }
                break;
            case 2:
                System.out.println("Ange konsert id");
                crud = new CRUD(sessionFactory);
                int in = scan.nextInt();
                crud.updateTicket(c, in);
                System.out.println("Du har valt " + crud.getConcertById(in));
                break;
            case 3:
                crud = new CRUD(sessionFactory);
                String street = c.getAdress().getStreet();
                int houseNum = c.getAdress().getHouseNumber();
                int zip = c.getAdress().getZipCode();
                String city = c.getAdress().getCity();
                scan.nextLine();
                System.out.println("Ange förnamn");
                String name = scan.nextLine();
                System.out.println("Ange efternamn");
                String lastName = scan.nextLine();
                System.out.println("Ange telefonnummer");
                String phone = scan.nextLine();
                crud.updateClient(c.getClientId(), name, lastName, phone, street, houseNum, zip, city);
                System.out.println("Dina användaruppgifter är uppdaterade");
                break;
            case 4:
                System.out.println("Ange konsert id");
                crud = new CRUD(sessionFactory);
                int in2 = scan.nextInt();
                crud.deleteTicket(c.getClientId(), in2);
                System.out.println("Du har avbokat din biljett");
                break;
            case 5:
                run = false;
                break;
            default: break;
        }
    }
    public void start(SessionFactory sessionFactory){
        while (run){
            showMenu();
            int input = intInput("");
            action(input, sessionFactory);
        }
    }
    public void logIn(SessionFactory sessionFactory) {
        boolean wh = true;
        while(wh) {
            Session session = sessionFactory.openSession();
            System.out.println("Vänligen mata in ditt id");
            int id = scan.nextInt();
            c = session.get(Client.class, id);
            if (c.getClientId() == 1) {
                System.out.println("Ange lösenord");
                int password = scan.nextInt();
                if (password == 123) {
                    admin(sessionFactory);
                    wh = false;

                }
            }
               else if ((c != null) && (c.getClientId() > 1)) {
                    System.out.println("Hej och välkommen " + c.getFirstName() + " " + c.getLastName());
                    start(sessionFactory);
                    wh = false;

                } else {
                    System.out.println("Ingen klient med detta id finns i databasen");
                }
            }


        }


    public void admin(SessionFactory sessionFactory){
        boolean ok = true;
        while (ok){
            System.out.println("-------------------------");
            System.out.println("|   Wigell Concerts     |");
            System.out.println("|  Concerts and events  |");
            System.out.println("-------------------------");
            System.out.println("");

            System.out.println("Menu:");
            System.out.println("1. Lista bokningar");
            System.out.println("2. Administrera");
            System.out.println("3. Avsluta");
            int choice = scan.nextInt();
            switch(choice){
                case 1:
                    Session session = sessionFactory.openSession();
                    Query query = session.createQuery("FROM Concert");
                    List<Concert> result = query.list();
                    for(Concert o : result){
                        System.out.println(o.getArtistName() + " har följande kunder");
                        for(int i = 0; i < o.getClients().size(); i++){
                            System.out.println(o.getClients().get(i).getFirstName());
                        }
                    }
                    System.out.println(" ");
                    break;
                case 2:
                    administration(sessionFactory);
                    break;
                case 3:
                    ok = false;
                    run = false;
                    break;
            }
        }
    }
    public void administration(SessionFactory sessionFactory){
        System.out.println("1. Lägg till konsert");
        System.out.println("2. Ta bort konsert");
        System.out.println("3. Lägg till arena");
        System.out.println("4. Ta bort arena");
        System.out.println("5. Lägg till kund");
        System.out.println("6. Ta bort kund");

        int choice = scan.nextInt();
        switch(choice){
            case 1:
                crud = new CRUD(sessionFactory);
                scan.nextLine();
                System.out.println("Ange artistnamn");
                String artist = scan.nextLine();
                System.out.println("Ange datum");
                String date = scan.nextLine();
                System.out.println("Ange pris");
                int price = scan.nextInt();
                System.out.println("Ange arena id");
                int id = scan.nextInt();
                System.out.println("Ange åldersgräns");
                int age = scan.nextInt();
                Arena arena = crud.getArenaById(id);
                Concert concert = new Concert(artist, date, price, arena, age);
                crud.addConcert(concert);
                System.out.println("Konserten är tillagd");
                break;
            case 2:
                crud = new CRUD(sessionFactory);
                System.out.println("Ange konsertens id");
                int id3 = scan.nextInt();
                crud.deleteConcert(id3);
                System.out.println("Konserten är borttagen");
                break;
            case 3:
                scan.nextLine();
                crud = new CRUD(sessionFactory);
                System.out.println("Ange namn");
                String name = scan.nextLine();
                System.out.println("Ange typ");
                String type = scan.nextLine();
                System.out.println("Ange gata");
                String street = scan.nextLine();
                System.out.println("Ange husnummer");
                int houseNumber = scan.nextInt();
                System.out.println("Ange postnummer");
                int zipCode = scan.nextInt();
                scan.nextLine();
                System.out.println("Ange stad");
                String city = scan.nextLine();
                Adress adress = new Adress(street, houseNumber, zipCode, city);
                crud.addAdress(adress);
                Arena arena3 = new Arena(name, adress, type);
                crud.addArena(arena3);
                System.out.println("Arenan är tillagd");
                break;
            case 4:
                crud = new CRUD(sessionFactory);
                System.out.println("Ange arenans id");
                int idA = scan.nextInt();
                crud.deleteArena(idA);
                System.out.println("Arenan är borttagen");
                break;
            case 5:
                scan.nextLine();
                crud = new CRUD(sessionFactory);
                System.out.println("Ange förnamn");
                String firstName = scan.nextLine();
                System.out.println("Ange efternamn");
                String lastName = scan.nextLine();
                System.out.println("Ange födelsedatum");
                String birthday = scan.nextLine();
                System.out.println("Ange telefonnummer");
                String phoneNum = scan.nextLine();
                System.out.println("Ange gata");
                String street1 = scan.nextLine();
                System.out.println("Ange husnummer");
                int houseNumber1 = scan.nextInt();
                System.out.println("Ange postnummer");
                int zipCode1 = scan.nextInt();
                scan.nextLine();
                System.out.println("Ange stad");
                String city1 = scan.nextLine();
                Adress adress1 = new Adress(street1, houseNumber1, zipCode1, city1);
                crud.addAdress(adress1);
                Client client = new Client(firstName, lastName, birthday, phoneNum, adress1);
                crud.addClient(client);
                System.out.println("Kunden är tillagd");
                break;
            case 6:
                scan.nextLine();
                crud = new CRUD(sessionFactory);
                System.out.println("Ange kundens id");
                int id4 = scan.nextInt();
                crud.deleteClient(id4);
                System.out.println("Kunden är borttagen");
                break;
        }
    }

}
