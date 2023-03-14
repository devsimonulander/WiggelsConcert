package se.yalar;

import java.util.Scanner;

public class Gameloop {
    private Scanner scan;
    private String validInput[];

    public Gameloop(){
        scan = new Scanner(System.in);
        validInput = new String[]{"1","2","3","4"};
    }

    public String stringInput(String message, String[] validInputs) {
        boolean isDone = false;

        while (!isDone) {
            System.out.println(message);
            System.out.print("> ");
            String input = scan.nextLine();
            for (String s : validInputs) {
                if (s.toLowerCase().equals(input.toLowerCase())) {
                    return input;
                }
            }
            System.out.println("Invalid input!");
        }
        return null;
    }

    public void showMenu(){
        System.out.println("Menu:");
        System.out.println("1.");
        System.out.println("2.");
        System.out.println("3.");
        System.out.println("4.");
    }

    public void action(String action){
        switch(action){
            case 1:
            case 2:
            case 3:
            case 4:
            default: break;
        }
    }
    public void start(){
        while (true){
            showMenu();
            String input = stringInput("",validInput);
            action(input);
        }
    }

}
