import UI.UserInterface;
import members.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import filehandling.FileHandler;

public class Controller {

  private boolean running = true;
  private FileHandler fileHandler = new FileHandler();          // Maybe/maybe-not remove from cr?
  Scanner sc = new Scanner(System.in);
  UserInterface ui = new UserInterface();
  Creator cr = new Creator();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy");    // minutes?? Hmm, let's test...

  // seems legit!
  public Controller() throws FileNotFoundException {
  }

  public void run() throws FileNotFoundException {

    cr.loadMembers(); //Loads members from /src/data/members.csv

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

    System.out.println(cr.getMemberList());
    while (running) {

      ui.formandUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
        case 1 -> foremanNewMember();
        case 2 -> System.out.println("Not done");
        case 3 -> {
          ui.typeMemberID();
          String memberID = sc.next();
          findMember(memberID, cr.getMemberList()).toggleStatus();
          String activeMember = findMember(memberID, cr.getMemberList()).getActive();
          ui.statusAltered(activeMember);
        }
        case 4 -> {
          ui.typeMemberID();
          String memberID = sc.next();
          findMember(memberID, cr.getMemberList()).toggleCompetitive();
          String competionSwimmer = findMember(memberID, cr.getMemberList()).getCompetitive();
          ui.typeAltered(competionSwimmer);
        }
        case 5 -> {
          ui.typeMemberID();
          String memberID = sc.next();                                  // nextLine crasher i runtime 1
          ui.nameChange();
          //String newName = sc.next();                                   // nextLine indsætter tomt felt
          String firstName = sc.next();
          String middleName = sc.next();
          String surname = sc.next();
          String newName = (firstName + " " + middleName + " " + surname);
          findMember(memberID, cr.getMemberList()).setName(newName);    // CRASHER! InputMismatchException
          fileHandler.saveMembersToCSV(cr.getMemberList());             // throwFor, next, nextInt, nextInt
        }
        case 6 -> ui.printMemberListTable(cr.getMemberList());
        case 7 -> {
          fileHandler.saveMembersToCSV(cr.getMemberList());
          run();
        }
        case 8 -> exit();
      }
    }
  }

  public void kasserer() throws FileNotFoundException {
    while (running) {

      ui.kassererUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
        case 1 -> System.out.println("Not done, son");
        // case 2 -> subscription();
        case 3 -> run();
        case 4 -> exit();
      }
    }
  }

  public void træner() throws FileNotFoundException {
    while (running) {

      ui.traenerUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
//        case 1 -> coachViewSchedule();
//        case 2 -> coachNewEvent();
        //case 3 -> ui.Events();
//        case 4 -> coachAssignAthleteToComp();
        case 5 -> {
          ui.inputSwimmerID();
          String memberID = sc.next();
//          ui.inputDicipline();
//          int option = sc.nextInt();      // 1-4, but actually, we should get this value from recent Achievement
          ui.inputDistance();
          int distance = sc.nextInt();
//          ui.inputDate();                 // Shouldn't be input, should come from System
//          LocalDate somethingsomething;
          ui.inputTime();                   // Ideally, put in LocalDateTime, due to the close relationship
          ui.addCommendation();
          String commendation = sc.next();
          if (commendation.equalsIgnoreCase("ja")) {
            ui.commDescr();
            //selectMedal();
          } else if (commendation.equalsIgnoreCase("nej")) {
            ui.specialCommDescr();
            String awardedComm = sc.nextLine();
          } else {
            ui.badInput();
          }
          //findMember(memberID, cr.getMemberList()).logResult();
        }
        case 6 -> run();
        case 7 -> exit();
      }
    }
  }

  public void exit() {
    running = false;
  }

  public void foremanNewMember() throws FileNotFoundException {
    boolean competition = false;
    ui.memberName();
    String name = sc.nextLine();
    ui.dateOfBirth();
    String birthdate = sc.next();
    int first = birthdate.indexOf(".");
    int second = birthdate.lastIndexOf(".");
    int date = Integer.valueOf(birthdate.substring(0, first));
    int month = Integer.valueOf(birthdate.substring(first + 1, second));
    int year = Integer.valueOf(birthdate.substring(second + 1));
    LocalDate newDate = LocalDate.of(year, month, date);

    ui.competetive();
    String competitive = sc.next();
    if (competitive.equalsIgnoreCase("ja")) {
      competition = true;
    } else if (competitive.equalsIgnoreCase("nej")) {
      competition = false;
    } else {
      ui.badInput();
    }

    ui.phoneNumber();
    String phoneNumber = sc.next();

    ui.email();
    String email = sc.next();

    cr.createNewMember(name, newDate, phoneNumber, email, competition, true);
    sortBy();
  }

  /*public double subscription(){
    double subscription;
    double subscriptionJunior = 1000;
    double subscriptionSenior = 1600;
    double subscriptionRetired = 1200;
    double subscriptionPassive = 500;

    if(active == true){
      if(Member.equals(Junior)){ //alternativt en funktion der checker om alderen er under 18
        subscription = subscriptionJunior;
      } else if(Member.equals(Senior)){ //alternativt en funktion der checker om alderen er over 18
        subscription = subscriptionSenior;
      } else subscription = subscriptionRetired;
    } else subscription = subscriptionPassive;
    return subscription;
  }

  public void subscriptionIncome() {
    double income = 0;
    for (double i = 0; i < cr.getList().size(); i++) {
      income += cr.getList().size().get(i).subscription();
    }
    System.out.println("Subscription income:" + income);
    }*/

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

