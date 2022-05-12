import UI.UserInterface;

import java.util.Scanner;

import members.Junior;

import java.util.Date;

public class Controller {
    private boolean running = true;
    Scanner sc = new Scanner(System.in);
    UserInterface ui = new UserInterface();
    Creator cr = new Creator();

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
        cr.addMember(new Junior("Tobias", new Date(99, 7, 8), false, false));
        cr.addMember(new Junior("Andreas", new Date(23, 2, 83), true, true));

        System.out.println(cr.giveUserID());
        System.out.println(cr.getMemberList());
    }

    public void kasserer(){

    }

    public void træner(){

    }

    public void exit(){
        running = false;
    }
}
