import UI.UserInterface;
import members.Creator;
import members.Junior;
import members.MemberList;

import java.io.FileNotFoundException;
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

    System.out.println(cr.getMemberList());
    while (running) {

      ui.formandUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
        case 1 -> formandNewMember();
        case 2 -> System.out.println("Not done");
        case 3 -> System.out.println("Not done");
        case 4 -> {
          ui.typeMemberID();
          String memberID = sc.next();
          ui.nameChange();
          String newName = sc.next();
          cr.getMemberList().findMember(memberID, cr.getMemberList()).setName(newName);
        }
        case 5 -> ui.printMemberList(cr.getMemberList());
        case 6 -> run();
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

  public void formandNewMember() {
    boolean competition = false;
    ui.memberName();
    String name = sc.next();
    ui.dayOfBirth();
    int day = sc.nextInt();
    ui.monthOfBirth();
    int month = sc.nextInt();
    ui.yearOfBirth();
    int year = sc.nextInt() - 1900; //1900 fratrækkes da det som default lægges til det indtastede fødselsår hvilket giver problemer for fødselsdatoer efter 1999.
    Date newDate = new Date(year, month, day);
    ui.competetive();
    String competetive = sc.next();
    if (competetive.equalsIgnoreCase("ja")) {
      competition = true;
    } else if (competetive.equalsIgnoreCase("nej")) {
      competition = false;
    } else {

    }
    cr.createNewMember(name, newDate, competition, true);
  }
}

