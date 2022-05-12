import UI.UserInterface;

import java.util.Scanner;

public class Controller {
    private boolean running = true;
    Scanner sc = new Scanner(System.in);
    UserInterface ui = new UserInterface();
    public void run(){
        while (running){
            ui.startupMenu();
            int input = sc.nextInt();
            sc.nextLine(); //Scannerbug fix
            switch (input){
                case 1 -> formand();
                case 2 -> kasserer();
                case 3 -> træner();
                case 4 -> exit();
            }
        }
    }

    public void formand(){

    }

    public void kasserer(){

    }

    public void træner(){

    }

    public void exit(){
        running = false;
    }
}
