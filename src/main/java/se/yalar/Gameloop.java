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

    public int intInput(String message) {
        boolean isDone = false;

        while (!isDone) {
            System.out.println(message);
            System.out.print("> ");
            int input = scan.nextInt();
                if(input > 0) {
                    return input;
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
            System.out.println("2. Avsluta");
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
                    ok = false;
                    run = false;
                    break;
            }
        }




    }

}
