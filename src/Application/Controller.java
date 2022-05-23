package Application;

import UI.UserInterface;
import members.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
  MemberManager memberManager = new MemberManager();
  Creator cr = new Creator();
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
          findMember(memberID).toggleStatus();
          String activeMember = findMember(memberID).getActive();
          ui.statusAltered(activeMember);
        }
        case 4 -> {
          ui.typeMemberIDForNameChange();
          String memberID = sc.next();
          findMember(memberID).toggleCompetitive();
          String competionSwimmer = findMember(memberID).getCompetitive();
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
          findMember(memberID).setName(newName);
          fileHandler.saveMembersToCSV(memberManager.getMemberList());
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
 //         ArrayList<Achievement> proficiency = findMember(memberID, cr.getMemberList()).getProficiency();

        //  Achievement registered = cr.newAchievement(DateTime, discipline, distance, commendation);
        //  findMember(memberID, cr.getMemberList()).logResult(registered, proficiency);
        }
        case 6 -> {
          ui.top5AgeUI();
          int ageChoice = sc.nextInt();
          sc.nextLine(); //Scannerbug fix
          if (ageChoice == 1){
            top5Gender(memberManager.sortSenior());
          }
          else if (ageChoice == 2){
            top5Gender(memberManager.sortJunior());
          }
        }
        case 7 -> run();
        case 8 -> exit();
      }
    }
  }

  public void top5Gender(ArrayList<Member> member) {
    boolean running = true;
    while (running){
      ui.top5GenderUI();
      int genderChoice = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      if (genderChoice == 1){
        top5Style(mensList(member));
      }
      else if (genderChoice == 2){
        top5Style(womensList(member));
      }
    }
  }

  public void top5Style(ArrayList<Member> member){
    boolean running = true;
    while (running){
      ui.top5StyleUI();
      int styleChoice = sc.nextInt();
      sc.nextLine(); //Scannerbug fix
      switch (styleChoice){
       case 1 -> {ui.printTop5(sortBy(2, member));}
       case 2 -> {sortBy(3, member);}
       case 3 -> {sortBy(4, member);}
       case 4 -> {sortBy(5, member);}
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
    String gender = sc.next();
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

    String tempID = " ";
    double noArrears = 0;
    memberManager.createNewMember(name, tempID, gender, newDate, phoneNumber, email, competition, noArrears, true);
    sortBy(1, memberManager.getList());
  }

  public int subscription(int i) {
    int subscription = 0;
    int subscriptionJunior = 1000;
    int subscriptionSenior = 1600;
    int subscriptionRetired = 1200;
    int subscriptionPassive = 500;



      if (memberManager.getList().get(i) instanceof Junior) {
        if (memberManager.getList().get(i).getActiveBool()) {
          subscription += subscriptionJunior;
        } else {
          subscription += subscriptionPassive;
        }

      } else if (memberManager.getList().get(i) instanceof Senior) {
        LocalDate now = LocalDate.now();
        Period p = Period.between(memberManager.getList().get(i).getBirth(), now);
        if (memberManager.getList().get(i).getActiveBool()) {
          subscription += subscriptionSenior;
        } else if (p.getYears() >= 60) {
          subscription += subscriptionRetired;
        } else if (!memberManager.getList().get(i).getActiveBool()) {
          subscription += subscriptionPassive;
        }
      }

    return subscription;
  }

  public void subscriptionIncome(){
    int income = 0;
    for (int i = 0; i < memberManager.getMemberList().getList().size(); i++) {
      income += subscription(i);
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

    String timeString = sc.next();
    int colonIndex = timeString.indexOf(":");
    int hours = Integer.parseInt(timeString.substring(0, colonIndex));
    int minutes = Integer.parseInt(timeString.substring(colonIndex + 1, timeString.length()));

    LocalDateTime eventTime = LocalDateTime.of(year, month, day , hours, minutes);

    cr.createNewEvent(eventName, category, league, eventTime);
  }

  public ArrayList<Member> sortBy(int sort, ArrayList<Member> member) {

    switch (sort) {
      case 1 -> {
        //Alfabetisk sortering - virker
        Collections.sort((List<Member>) member, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
      }
      case 2 -> {
        //Numerisk sortering af svømmeresultater (opdelt i discipliner) til hver svømmer - ikke testet
        for (int i = 0; i <= member.size(); i++) {

          //Sortering af backcrawl og indv. top 3
          Collections.sort((List<Achievement>) member.get(i).getBackcrawlResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3backstroke(member);
        }
      }
      case 3 -> {
        for (int i = 0; i <= member.size(); i++) {
          Collections.sort((List<Achievement>) member.get(i).getBreaststrokeResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3breaststroke(member);
        }
      }
      case 4 -> {     //Sortering af butterfly og indv. top3 for videre sortering
        for (int i = 0; i <= member.size(); i++) {
          Collections.sort((List<Achievement>) member.get(i).getButterflyResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3butterfly(member);
        }
      }
      case 5 -> {
        //Achievement listerne sorteres herunder for hver bruger hvorefter de tre bedste resultater flyttes til en Array for videre sortering af top5
        for (int i = 0; i <= member.size(); i++) {
          Collections.sort((List<Achievement>) member.get(i).getCrawlResults(), (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
          top3crawl(member);
        }
      }
      case 6 -> {
        //Oprettelse af indv. top3 for hver svømmer
        Collections.sort((List<Member>) member, (o1, o2) -> o1.getTempTop3()[0].compareTo(o2.getTempTop3()[0]));
      }
    }
    return member;
  }

  public Member findMember(String userID) {
    for (int i = 0; i < memberManager.getList().size(); i++) {
      if (memberManager.getList().get(i).getMemberID().equals(userID))
        return memberManager.getList().get(i);
    }
    return null;
  }

    public void top3crawl (ArrayList<Member> member) {
      for (int i = 0; i < member.size(); i++) {
        for (int o = 0; o < 3; o++) {
          member.get(i).setTempTop3(o, member.get(i).getCrawlResults().get(o));
        }
      }
    }

    public void top3butterfly (ArrayList<Member> member) {
      for (int i = 0; i < member.size(); i++) {
        for (int o = 0; o < 3; o++)
          member.get(i).setTempTop3(o, member.get(i).getButterflyResults().get(o));
      }
    }


    public void top3backstroke (ArrayList<Member> member) {
      for (int i = 0; i < member.size(); i++) {
        for (int o = 0; o < 3; o++) {
          member.get(i).setTempTop3(o, member.get(i).getBackcrawlResults().get(o));
        }
      }
    }

    public void top3breaststroke (ArrayList<Member> member) {
      for (int i = 0; i < member.size(); i++) {
        for (int o = 0; o < 3; o++)
          member.get(i).setTempTop3(o, member.get(i).getBreaststrokeResults().get(o));
      }



    }
  public ArrayList<Member> womensList(ArrayList<Member> member) {
    ArrayList<Member> women = new ArrayList<>();
    for (int i = 0; i < member.size(); i++)
      if (member.get(i).getGender().equals("Kvinde")) {
        women.add(member.get(i));
      }
    return women;
  }

  public ArrayList<Member> mensList(ArrayList<Member> member) {
    ArrayList<Member> men = new ArrayList<Member>();
    for (int i = 0; i < member.size(); i++)
      if (member.get(i).getGender().equals("Mand")) {
        men.add(member.get(i));
      }
    return men;
  }
  }


