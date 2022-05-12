import UI.UserInterface;

import java.util.Date;
import java.util.Scanner;

public class Controller {
    private boolean running = true;
    Scanner sc = new Scanner(System.in);
    UserInterface ui = new UserInterface();
    Creator cr = new Creator();

    public void run() {
        while (running) {
            ui.startupMenu();
            int input = sc.nextInt();
            sc.nextLine(); //Scannerbug fix
            switch (input) {
                case 1 -> formand();
                case 2 -> kasserer();
                case 3 -> træner();
                case 4 -> exit();
            }
        }
    }

    public void formand() {
        while (running) {
          boolean competition;
          ui.formandUI();
            int input = sc.nextInt();
            sc.nextLine(); //Scannerbug fix
            switch (input) {
                case 1 -> {ui.memberName();
                            String name = sc.next();
                            ui.dayOfBirth();
                            int day = sc.nextInt();
                            ui.monthOfBirth();
                            int month = sc.nextInt();
                            ui.yearOfBirth();
                            int year = sc.nextInt();
                            Date newDate = new Date(day, month, year);
                            ui.competetive();
                            String competetive = sc.next();
                            if(competetive.equalsIgnoreCase("ja")){
                              competition = true;
                            }
                            else if(competetive.equalsIgnoreCase("nej")){
                              competition = false;
                            }
                            else{

                            }
                            cr.createNewMember(name, newDate, competition, true);
                }
                case 2 -> // delete member from list();
                case 3 -> // set member to passive/active;
                case 4 -> exit();
            }
        }
    }
            public void kasserer() {

            }

            public void træner() {

            }

            public void exit() {
                running = false;
            }


}
