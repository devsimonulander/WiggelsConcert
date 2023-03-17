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
        boolean isDone = false;

        while (!isDone) {
            System.out.println(message);
            System.out.print("> ");
            String input = scan.nextLine();
            if (hasOnlyDigits(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Invalid input!");
        }
        return 0;
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
        System.out.println("3. Avsluta");
        System.out.println("4.");
    }

    public void action(int action, SessionFactory sessionFactory){
        switch(action){
            case 1:
                crud = new CRUD(sessionFactory);
                List<Concert> conserts = crud.getAllConcerts();
                for(int i = 0; i < conserts.size(); i++){
                    System.out.println(conserts.get(i).toString());
                }
            case 2:
                System.out.println("Ange konsert id");
                crud = new CRUD(sessionFactory);
                int in = scan.nextInt();
                crud.updateTicket(c, in);
                System.out.println("Du har valt " + crud.getConcertById(in));
            case 3:
                run = false;
            case 4:
            default: break;
        }
    }
    public void start(SessionFactory sessionFactory){
        while (run){
            showMenu();
            int input = stringInput("",validInput);
            action(input, sessionFactory);
        }
    }
    public void logIn(SessionFactory sessionFactory){
        Session session = sessionFactory.openSession();
        System.out.println("Vänligen mata in ditt id");
        int id = scan.nextInt();
        c = session.get(Client.class,id);
        if((c != null)||(c.getClientId() < 99) ) {
            System.out.println("hej och välkommen " +c.getFirstName() + " " +  c.getLastName());
            start(sessionFactory);
            } else if(c.getClientId() == 99) {
            System.out.println("Ange lösenord");
            int password = scan.nextInt();
            if(password == 123){
                admin(sessionFactory);
        }else {
                System.out.println("Ingen klient med detta id finns i databasen");
            }
        }
    }
    public void admin(SessionFactory sessionFactory){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Concert");
        List<Concert> result = query.list();
        for(Concert o : result){
            System.out.println(o.getArtistName() + " har följande kunder");
            for(int i = 0; i < o.getClients().size(); i++){
                System.out.println(o.getClients().get(i).getFirstName());
            }
        }
    }

}
