package filehandling;

import members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
      printStream.print(achievement.getPlacement());
      printStream.print(";");
      printStream.print(achievement.getComment());
      printStream.print(";");
      printStream.print(achievement.getEvent());
      printStream.print(";");
      printStream.print("\n");
    }
    ui.savedMessage();
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
        case "BUTTERFLY" -> discipline = Discipline.BUTTERFLY;
        case "CRAWL" -> discipline = Discipline.CRAWL;
        case "BACKSTROKE" -> discipline = Discipline.BACKSTROKE;
        case "BREASTSTROKE" -> discipline = Discipline.BREASTSTROKE;
      }

      String dateString = lineScanner.next();
      LocalDateTime time = LocalDateTime.parse(dateString);

      int minute = time.getMinute();
      int second = time.getSecond();
      int hour = time.getHour();
      int day = time.getDayOfMonth();
      int month = time.getMonthValue();
      int year = time.getYear();

      int distance = lineScanner.nextInt();
      //String medalString = lineScanner.next();

      int placement = lineScanner.nextInt();

      Medal medal = null;

      switch (placement){
        case 1 -> medal = Medal.GOLD;
        case 2 -> medal = Medal.SILVER;
        case 3 -> medal = Medal.BRONZE;
        default -> medal = null;
      }

      String comment = lineScanner.next();

      String event = lineScanner.next();

      Achievement achievement;

      //if (memberID.equals(controller.findMember(memberID).getMemberID())) {
        LocalDateTime lc = LocalDateTime.of(year, month, day, hour, minute, second);
        achievement = new Achievement(discipline, lc, distance);
        achievement.setMemberID(memberID);
        achievement.setPlacement(placement);
        achievement.setComment(comment);
        achievement.setEvent(event);
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

  public void saveAchievementsToCSV(AchievementList achievementList) throws FileNotFoundException {
    PrintStream out = new PrintStream(resultsCSV);
    saveAchievementsMethod(out, achievementList.getAchievements());
  }

  public boolean calculateIfJunior(LocalDate dateIn) {
    LocalDate now = LocalDate.now();
    LocalDate birthDate = LocalDate.of(dateIn.getYear(), dateIn.getMonth(), dateIn.getDayOfMonth());

    Period p = Period.between(birthDate, now);

    return !(p.getYears() >= 18);
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
