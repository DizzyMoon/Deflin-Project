package filehandling;

import Application.Controller;
import members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Achievement.*;

import UI.UserInterface;

public class FileHandler {
  Scanner sc = new Scanner(System.in);
  boolean running = true;
  File membersCSV = new File("src/data/members.csv");
  File resultsCSV = new File("src/data/results.csv");
//File eventsCSV = new File("src/data/events.csv");


  public FileHandler() throws FileNotFoundException {
  }

  public boolean hasSavedData() {
    if (membersCSV.isFile()) {
      return true;
    }
    return false;
  }

  public void saveMembersToCSV(MemberList memberList) throws FileNotFoundException {
    PrintStream out = new PrintStream(membersCSV);
    saveMembersMethod(out, memberList.getList());
  }

  public ArrayList<Member> loadMemberList() throws FileNotFoundException {

    ArrayList<Member> newMemberList = new ArrayList<Member>();

    Scanner fileScanner = new Scanner(membersCSV);
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();

      Scanner lineScanner = new Scanner(line).useDelimiter(";");

      String name = lineScanner.next();
      String memberID = lineScanner.next();
      String gender = lineScanner.next();
      String birthString = lineScanner.next();
      String phoneNumber = lineScanner.next();
      String email = lineScanner.next();
      String competitiveString = lineScanner.next();
      String arrearsString = lineScanner.next();
      String activeString = lineScanner.next();

      ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();

      LocalDate birth = LocalDate.parse(birthString);
      boolean competitive = competitiveString.equals("Konkurrencesvømmer");
      boolean active = activeString.equals("Aktiv");
      boolean arrears = arrearsString.equals("Betalt");

      Member newMember;

      if (calculateIfJunior(birth)) {
        newMember = new Junior(name, memberID, gender, birth, phoneNumber, email, competitive, arrears, active);
      } else {
        newMember = new Senior(name, memberID, gender, birth, phoneNumber, email, competitive, arrears, active);
      }

      newMemberList.add(newMember);

    }
    return newMemberList;
  }



  public ArrayList<Achievement> achievementListLoad() throws FileNotFoundException{
    Scanner fileScanner = new Scanner(resultsCSV);
    ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();

    while (fileScanner.hasNextLine()) {


      String line = fileScanner.nextLine();

      Scanner lineScanner = new Scanner(line).useDelimiter(";");
      String memberID = lineScanner.next();
      String stringDiscipline = lineScanner.next();
      Discipline discipline = Discipline.BUTTERFLY;

      switch (stringDiscipline){
        case "butterfly" -> discipline = Discipline.BUTTERFLY;
        case "crawl" -> discipline = Discipline.CRAWL;
        case "ryg" -> discipline = Discipline.BACKSTROKE;
        case "bryst" -> discipline = Discipline.BREASTSTROKE;
      }

      int minute = lineScanner.nextInt();
      int second = lineScanner.nextInt();
      int distance = lineScanner.nextInt();
      String medalString = lineScanner.next();
      Medal medal = Medal.GOLD;

      switch (medalString){
        case "GOLD" -> medal = Medal.GOLD;
        case "SILVER" -> medal = Medal.SILVER;
        case "BRONZE" -> medal = Medal.BRONZE;
      }


      Achievement achievement;

      //if (memberID.equals(controller.findMember(memberID).getMemberID())) {
        LocalDateTime lc = LocalDateTime.of(2020, 10, 10, 0, minute, second);
        achievement = new Achievement(discipline, lc, distance);
        achievement.setMemberID(memberID);
        achievement.setMedal(medal);
        /*switch (discipline){
          case "backstroke" -> controller.findMember(memberID).setBackstrokeResults(achievement);
          case "crawl" -> controller.findMember(memberID).setCrawlResults(achievement);
          case "breaststroke" -> controller.findMember(memberID).setBreastStroke(achievement);
          case "butterfly" -> controller.findMember(memberID).setButterflyResults(achievement);
        }*/
        tempAchievementList.add(achievement);
      }
    return tempAchievementList;
    }





/*
  public int getMonthNumber(String month) {       // Is this just a homemade getMonthValue? It's not called?
    int monthNum = 0;
    switch (month) {
      case "Jan" -> {
        monthNum = 1;
      }
      case "Feb" -> {
        monthNum = 2;
      }
      case "Mar" -> {
        monthNum = 3;
      }
      case "Apr" -> {
        monthNum = 4;
      }
      case "May" -> {
        monthNum = 5;
      }
      case "Jun" -> {
        monthNum = 6;
      }
      case "Jul" -> {
        monthNum = 7;
      }
      case "Aug" -> {
        monthNum = 8;
      }
      case "Sep" -> {
        monthNum = 9;
      }
      case "Oct" -> {
        monthNum = 10;
      }
      case "Nov" -> {
        monthNum = 11;
      }
      case "Dec" -> {
        monthNum = 12;
      }
    }
    return monthNum;
  }
*/

  public boolean calculateIfJunior(LocalDate dateIn) {
    LocalDate now = LocalDate.now();
    LocalDate birthDate = LocalDate.of(dateIn.getYear(), dateIn.getMonth(), dateIn.getDayOfMonth());

    Period p = Period.between(birthDate, now);

    return !(p.getYears() >= 18);
  }

  public void saveAchievementsToCSV(AchievementList achievementList) throws FileNotFoundException {
    PrintStream out = new PrintStream(membersCSV);
    saveAchievementsMethod(out, achievementList.getAchievements());
  }

  public void saveAchievementsMethod(PrintStream printStream, ArrayList<Achievement> achievements){

    UserInterface ui = new UserInterface();

    ui.savingMessage();
    for (Achievement achievement : achievements){
      printStream.print(achievement.getMemberID());
      printStream.print(";");
      printStream.print(achievement.getDiscipline());
      printStream.print(";");
      printStream.print(achievement.getResult());
      printStream.print(";");
      printStream.print(achievement.getDistance());
      printStream.print(";");
      printStream.print(achievement.getMedal());
      printStream.print(";");
      printStream.print(achievement.getComment());
      printStream.print(";");
      printStream.print("\n");
    }
    ui.savedMessage();
  }

  public void saveMembersMethod(PrintStream printStream, ArrayList<Member> memberArrayList) {

    UserInterface ui = new UserInterface();

    ui.savingMessage();
    for (Member member : memberArrayList) {
      printStream.print(member.getName());
      printStream.print(";");
      printStream.print(member.getMemberID());
      printStream.print(";");
      printStream.print(member.getGender());
      printStream.print(";");
      printStream.print(member.getBirth());
      printStream.print(";");
      printStream.print(member.getPhoneNumber());
      printStream.print(";");
      printStream.print(member.getEmail());
      printStream.print(";");
      printStream.print(member.getCompetitive());
      printStream.print(";");
      printStream.print(member.getArrears());
      printStream.print(";");
      printStream.print(member.getActive());
      printStream.print(";");
      printStream.print("\n");
    }
    ui.savedMessage();
  }
}
