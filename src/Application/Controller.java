package Application;

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
  MemberManager memberManager = new MemberManager();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy");    // minutes?? Hmm, let's test...

  // seems legit!
  public Controller() throws FileNotFoundException {
  }

  public void run() throws FileNotFoundException {
    if (fileHandler.hasSavedData())
      memberManager.loadMembersFromCSV(); //Loads members from /src/data/members.csv

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
    while (running) {

      ui.formandUI();
      int input = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (input) {
        case 1 -> formandNewMember();
        case 2 -> removeMember();
        case 3 -> {
          ui.typeMemberIDForNameChange();
          String memberID = sc.next();
          findMember(memberID, memberManager.getMemberList()).toggleStatus();
          String activeMember = findMember(memberID, memberManager.getMemberList()).getActive();
          ui.statusAltered(activeMember);
        }
        case 4 -> {
          ui.typeMemberIDForNameChange();
          String memberID = sc.next();
          findMember(memberID, memberManager.getMemberList()).toggleCompetitive();
          String competionSwimmer = findMember(memberID, memberManager.getMemberList()).getCompetitive();
          ui.typeAltered(competionSwimmer);
        }
        case 5 -> {
          ui.typeMemberIDForNameChange();
          String memberID = sc.next();
          ui.typeMemberIDForNameChange();
          String memberID1 = sc.next();                                  // nextLine crasher i runtime 1
          ui.nameChange();
          //String newName = sc.next();                                   // nextLine indsætter tomt felt
          String firstName = sc.next();
          String middleName = sc.next();
          String surname = sc.next();
          String newName = (firstName + " " + middleName + " " + surname);
          findMember(memberID1, memberManager.getMemberList()).setName(newName);    // CRASHER! InputMismatchException
          fileHandler.saveMembersToCSV(memberManager.getMemberList());             // throwFor, next, nextInt, nextInt
        }
        case 6 -> ui.printMemberListTable(memberManager.getMemberList());
        case 7 -> {
          fileHandler.saveMembersToCSV(memberManager.getMemberList());
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

  public void removeMember() throws FileNotFoundException {
    ui.typeMemberIDForRemove();
    String UID = sc.nextLine();
    memberManager.removeMember(UID);
  }

  public void formandNewMember() throws FileNotFoundException {
    boolean competition = false;
    ui.memberName();
    String name = sc.nextLine();
    ui.gender();
    String gender = sc.next();
    ui.dateOfBirth();
    String birthdate = sc.next();
    int first = birthdate.indexOf(".");
    int second = birthdate.lastIndexOf(".");
    int date = Integer.valueOf(birthdate.substring(0, first));
    int month = Integer.valueOf(birthdate.substring(first + 1, second));
    int year = Integer.valueOf(birthdate.substring(second + 1));
    LocalDate newDate = LocalDate.of(year, month, date);

    ui.competitive();
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

    memberManager.createNewMember(name, gender, newDate, phoneNumber, email, competition, true);
    sortBy(1);
  }

  public int subscription(int i) {
    int subscription;
    int subscriptionJunior = 1000;
    int subscriptionSenior = 1600;
    int subscriptionRetired = 1200;
    int subscriptionPassive = 500;

    if (memberManager.getMemberList().getList().equals("aktivt")) {
      if (memberManager.getMemberList().getList().equals("Junior")) {
        subscription = subscriptionJunior;
      } else if (memberManager.getMemberList().getList().equals("Senior")) {
        subscription = subscriptionSenior;
      } else subscription = subscriptionRetired;
    } else subscription = subscriptionPassive;
    return subscription;
  }

  public void subscriptionIncome() {
    int income = 0;
    for (int i = 0; i < memberManager.getList().size(); i++) {
      income += memberManager.getList().size() * subscription(i);
    }
    System.out.println("Subscription income:" + income);
  }

  public void sortBy(int sort) {
    switch (sort) {
      case 1 -> {
        //Alfabetisk sortering - virker
        Collections.sort((List<Member>) memberManager.getList(), (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
      }
      case 2 -> {
        //Numerisk sortering af svømmeresultater (opdelt i discipliner) til hver svømmer - ikke testet
        for (int i = 0; i <= memberManager.getList().size(); i++) {

          //Sortering af backcrawl og indv. top 3
          Collections.sort((List<Achievement>) memberManager.getList().get(i).getBackcrawlResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3backCrawl();
        }
      }
      case 3 -> {
        for (int i = 0; i <= memberManager.getList().size(); i++) {
          Collections.sort((List<Achievement>) memberManager.getList().get(i).getBreaststrokeResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3breastStroke();
        }
      }
      case 4 -> {     //Sortering af butterfly og indv. top3 for videre sortering
        for (int i = 0; i <= memberManager.getList().size(); i++) {
          Collections.sort((List<Achievement>) memberManager.getList().get(i).getButterflyResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3butterfly();
        }
      }
      case 5 -> {
        //Achievement listerne sorteres herunder for hver bruger hvorefter de tre bedste resultater flyttes til en Array for videre sortering af top5
        for (int i = 0; i <= memberManager.getList().size(); i++) {
          Collections.sort((List<Achievement>) memberManager.getList().get(i).getCrawlResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3crawl();
        }
      }
      case 6 -> {
        //Oprettelse af indv. top3 for hver svømmer
        Collections.sort((List<Member>) memberManager.getList(), (o1, o2) -> o1.getTempTop3()[0].compareTo(o2.getTempTop3()[0]));
      }
    }
  }

    public Member findMember (String userID, MemberList memberList){
      for (int i = 0; i < memberList.getList().size(); i++) {
        if (memberList.getList().get(i).getMemberID().equals(userID))
          return memberList.getList().get(i);
      }
      return null;
    }

    public void top3crawl () {
      for (int i = 0; i < memberManager.getList().size(); i++) {
        for (int o = 0; o < 3; o++) {
          memberManager.getList().get(i).setTempTop3(o, memberManager.getList().get(i).getCrawlResults().get(o));
        }
      }
    }

    public void top3butterfly () {
      for (int i = 0; i < memberManager.getList().size(); i++) {
        for (int o = 0; o < 3; o++)
          memberManager.getList().get(i).setTempTop3(o, memberManager.getList().get(i).getButterflyResults().get(o));
      }
    }


    public void top3backCrawl () {
      for (int i = 0; i < memberManager.getList().size(); i++) {
        for (int o = 0; o < 3; o++) {
          memberManager.getList().get(i).setTempTop3(o, memberManager.getList().get(i).getBackcrawlResults().get(o));
        }
      }
    }

    public void top3breastStroke () {
      for (int i = 0; i < memberManager.getList().size(); i++) {
        for (int o = 0; o < 3; o++)
          memberManager.getList().get(i).setTempTop3(o, memberManager.getList().get(i).getBreaststrokeResults().get(o));
      }
    }
  }


