import UI.UserInterface;
import members.Creator;
import members.Junior;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Controller {
  private boolean running = true;
  Scanner sc = new Scanner(System.in);
  UserInterface ui = new UserInterface();
  Creator cr = new Creator();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd-YYYY");

  public Controller() throws FileNotFoundException {
  }

  public void run() throws FileNotFoundException {
  cr.loadMembers();
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

  public void formand() throws FileNotFoundException {

    //System.out.println(cr.giveUserID());
    //System.out.println(cr.getMemberList());
    while (running) {

      ui.formandUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
        case 1 -> formandNewMember();
                case 2 -> System.out.println("Not done");
                case 3 -> System.out.println("Not done");
                case 4 -> ui.printMemberList(cr.getMemberList());
                case 5 -> exit();
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

  public void formandNewMember() throws FileNotFoundException {
    boolean competition = false;
    ui.memberName();
    String name = sc.next();
    ui.dayOfBirth();
    int day = sc.nextInt();
    ui.monthOfBirth();
    int month = sc.nextInt();
    ui.yearOfBirth();
    int year = sc.nextInt(); //Slettet -1900
    LocalDate newDate = LocalDate.of(year, month, day);
    ui.competetive();
    String competitive = sc.next();
    if (competitive.equalsIgnoreCase("ja")) {
      competition = true;
    } else if (competitive.equalsIgnoreCase("nej")) {
      competition = false;
    } else {

    }
    cr.createNewMember(name, newDate, competition, true);
  }
}

