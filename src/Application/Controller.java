package Application;

import UI.UserInterface;
import members.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    if (fileHandler.hasSavedData())
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
          findMember(memberID, cr.getMemberList()).toggleStatus();
          String activeMember = findMember(memberID, cr.getMemberList()).getActive();
          ui.statusAltered(activeMember);
        }
        case 4 -> {
          ui.typeMemberIDForNameChange();
          String memberID = sc.next();
          findMember(memberID, cr.getMemberList()).toggleCompetitive();
          String competionSwimmer = findMember(memberID, cr.getMemberList()).getCompetitive();
          ui.typeAltered(competionSwimmer);
        }
        case 5 -> {
          ui.typeMemberIDForNameChange();
          String memberID = sc.next();                                  // nextLine crasher i runtime 1
          ui.nameChange();

/*    I'm tired of trying to do it the hard way:

          String keybdINput = sc.useDelimiter("\n").next();
          int firstSpace = keybdINput.indexOf(" ");
          int secondSpace = keybdINput.substring(firstSpace + 1).indexOf(" ");
          //String newName = sc.next();                                   // nextLine indsætter tomt felt
          String firstName = keybdINput.substring(0,firstSpace);
          String middleName = "blank";
          String surname = sc.next();

          if (secondSpace == -1) {
            secondSpace = firstSpace;
          } else {
            middleName = keybdINput.substring(firstSpace + 1, secondSpace);
          }

          String newName;
          String builtName;
          if (middleName != "blank") {
            builtName = (firstName + " " + middleName + " " + surname);
          } else {
            builtName = (firstName + " " + surname);
          }
          newName = builtName;

          // so for the time being, we'll just use the simple version*/
          String newName = sc.useDelimiter("\n").next();
          findMember(memberID, cr.getMemberList()).setName(newName);
          fileHandler.saveMembersToCSV(cr.getMemberList());
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
        case 1 -> subscriptionIncome();
        case 2 -> System.out.println("Not done, son");
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
        case 2 -> coachNewEvent();
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
          ArrayList<Achievement> proficiency = findMember(memberID, cr.getMemberList()).getProficiency();

        //  Achievement registered = cr.newAchievement(DateTime, discipline, distance, commendation);
        //  findMember(memberID, cr.getMemberList()).logResult(registered, proficiency);
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
    cr.removeMember(UID);
  }

  public LocalDate truncateToDate(LocalDateTime input)  {
    int d = input.getDayOfMonth();
    int m = input.getMonthValue();
    int y = input.getYear();
    LocalDate newDate = LocalDate.of(y,m,d);
    return newDate;
  }

  public LocalDateTime transformToDate(String dateFormat) {
    int first = dateFormat.indexOf(".");
    int second = dateFormat.lastIndexOf(".");
    int date = Integer.valueOf(dateFormat.substring(0, first));
    int month = Integer.valueOf(dateFormat.substring(first + 1, second));
    int year = Integer.valueOf(dateFormat.substring(second + 1));
    LocalDateTime newDate = LocalDateTime.of(year, month, date,0,0);
    return newDate;
  }

  public void formandNewMember() throws FileNotFoundException {
    boolean competition = false;
    ui.memberName();
    String name = sc.nextLine();
    ui.gender();
    String gender = sc. next();
    ui.dateOfBirth();
    LocalDate newDate = truncateToDate(transformToDate(sc.useDelimiter("\n").next()));
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

    cr.createNewMember(name, gender, newDate, phoneNumber, email, competition, true);
    sortBy(1);
  }

  public int subscription(int i) {
    int subscription;
    int subscriptionJunior = 1000;
    int subscriptionSenior = 1600;
    int subscriptionRetired = 1200;
    int subscriptionPassive = 500;

    if (cr.getMemberList().getList().equals("aktivt")) {
      if (cr.getMemberList().getList().equals("Junior")) {
        subscription = subscriptionJunior;
      } else if (cr.getMemberList().getList().equals("Senior")) {
        subscription = subscriptionSenior;
      } else subscription = subscriptionRetired;
    } else subscription = subscriptionPassive;
      return subscription;
    }

  public void subscriptionIncome() {
    int income = 0;
    for (int i = 0; i < cr.getList().size(); i++) {
      income += cr.getList().size() * subscription(i);
    }
    System.out.println("Subscription income:" + income);
    }

  public void coachNewEvent() {
    ui.promptEventName();
    String eventName = sc.useDelimiter("\n").next();
    ui.promptSubdivisionCode();
    boolean SDCvalid = false;
    String category = "";
    String categoryIN = "";
    boolean league = false;
    String leagueIN = "";

    while (!SDCvalid) {
      String input = sc.next();
      if (input.length() == 2) {
        categoryIN = input.substring(0, 1);
        leagueIN = input.substring(1, 2);
        if (categoryIN.equalsIgnoreCase("h") || categoryIN.equalsIgnoreCase("d")) {
          if (leagueIN.equalsIgnoreCase("j")) {
            category = categoryIN;
            league = true;
            SDCvalid = true;
          } else if (leagueIN.equalsIgnoreCase("s")) {
            category = categoryIN;
            league = false;
            SDCvalid = true;
            } else {
            ui.badInput();
          }
        }
      } else {
        ui.badInput();
      }
    }
    ui.promptEventDate();
    LocalDate tempTime = truncateToDate(transformToDate(sc.useDelimiter("\n").next()));
    ui.promptEventTime();
    int year = tempTime.getYear();
    int month = tempTime.getMonthValue();
    int day = tempTime.getDayOfMonth();
    int hours = sc.useDelimiter(":").nextInt();
    int minutes = sc.nextInt();
    LocalDateTime eventTime = LocalDateTime.of(year, month, day , hours, minutes);

    cr.createNewEvent(eventName, category, league, eventTime);
  }

  public void sortBy(int sort) {

    switch (sort) {
      case 1 -> {
        //Alfabetisk sortering - virker
        Collections.sort((List<Member>) cr.getList(), (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
      }
      case 2 -> {
        //Numerisk sortering af svømmeresultater (opdelt i discipliner) til hver svømmer - ikke testet
        for (int i = 0; i <= cr.getList().size(); i++) {

          //Sortering af backcrawl og indv. top 3
          Collections.sort((List<Achievement>) cr.getList().get(i).getBackcrawlResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3backstroke();
        }
      }
      case 3 -> {
        for (int i = 0; i <= cr.getList().size(); i++) {
          Collections.sort((List<Achievement>) cr.getList().get(i).getBreaststrokeResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3breastStroke();
        }
      }
      case 4 -> {     //Sortering af butterfly og indv. top3 for videre sortering
        for (int i = 0; i <= cr.getList().size(); i++) {
          Collections.sort((List<Achievement>) cr.getList().get(i).getButterflyResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3butterfly();
        }
      }
      case 5 -> {
        //Achievement listerne sorteres herunder for hver bruger hvorefter de tre bedste resultater flyttes til en Array for videre sortering af top5
        for (int i = 0; i <= cr.getList().size(); i++) {
          Collections.sort((List<Achievement>) cr.getList().get(i).getCrawlResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3crawl();
        }
      }
      case 6 -> {
        //Oprettelse af indv. top3 for hver svømmer
        Collections.sort((List<Member>) cr.getList(), (o1, o2) -> o1.getTempTop3()[0].compareTo(o2.getTempTop3()[0]));
      }
    }
  }

  public Member findMember(String userID, MemberList memberList) {
    for (int i = 0; i < memberList.getList().size(); i++) {
      if (memberList.getList().get(i).getMemberID().equals(userID))
        return memberList.getList().get(i);
    }
    return null;
  }

  public void top3crawl() {
    for (int i = 0; i < cr.getList().size(); i++) {
      for (int o = 0; o < 3; o++) {
        cr.getList().get(i).setTempTop3(o, cr.getList().get(i).getCrawlResults().get(o));
      }
    }
  }

  public void top3butterfly() {
    for (int i = 0; i < cr.getList().size(); i++) {
      for (int o = 0; o < 3; o++)
        cr.getList().get(i).setTempTop3(o, cr.getList().get(i).getButterflyResults().get(o));
    }
  }


  public void top3backstroke() {
    for (int i = 0; i < cr.getList().size(); i++) {
      for (int o = 0; o < 3; o++) {
        cr.getList().get(i).setTempTop3(o, cr.getList().get(i).getBackcrawlResults().get(o));
      }
    }
  }

  public void top3breastStroke() {
    for (int i = 0; i < cr.getList().size(); i++) {
      for (int o = 0; o < 3; o++)
      cr.getList().get(i).setTempTop3(o, cr.getList().get(i).getBreaststrokeResults().get(o));
    }
  }
}


