import UI.UserInterface;
import members.Creator;
import members.Member;
import members.MemberList;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Controller {
  private boolean running = true;
  Scanner sc = new Scanner(System.in);
  UserInterface ui = new UserInterface();
  Creator cr = new Creator();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");

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
          findMember(memberID, cr.getMemberList()).setName(newName);
        }
        case 5 -> ui.printMemberListTable(cr.getMemberList());
        case 6 -> run();
      }
    }
  }

  public void kasserer() {
    System.out.println(cr.getMemberList());
    while (running) {

      ui.kassererUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
        case 1 -> System.out.println("Not done, son");
        case 2 -> System.out.println("Not done, son");
        case 3 -> run();
        case 4 -> exit();
      }
    }
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
    ui.dateOfBirth();
    String birthdate = sc.next();
    int first = birthdate.indexOf(".");
    int second = birthdate.lastIndexOf(".");
    int date = Integer. valueOf(birthdate.substring(0, first));
    int month = Integer. valueOf(birthdate.substring(first + 1, second));
    int year = Integer. valueOf(birthdate.substring(second + 1));
    Date newDate = new Date(year, month, date);
    ui.competetive();
    String competetive = sc.next();
    if (competetive.equalsIgnoreCase("ja")) {
      competition = true;
    } else if (competetive.equalsIgnoreCase("nej")) {
      competition = false;
    }

    ui.phoneNumber();
    String phoneNumber = sc.next();

    ui.email();
    String email = sc.next();

    cr.createNewMember(name, newDate, phoneNumber, email, competition, true);
    sortBy();
  }

  public void sortBy() {
    Collections.sort((List<Member>) cr.getList());
  }

  public Member findMember(String userID, MemberList memberList) {
    for (int i = 0; i < memberList.getList().size(); i++) {
      if (memberList.getList().get(i).getMemberID().equals(userID))
        return memberList.getList().get(i);
    }
    return null;
  }

}

