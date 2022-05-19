package filehandling;

import members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;
import UI.UserInterface;

public class FileHandler {
  Scanner sc = new Scanner(System.in);
  boolean running = true;
  File membersCSV = new File("src/data/members.csv");



  public FileHandler() throws FileNotFoundException {
  }

  public void saveMembersToCSV(MemberList memberList) throws FileNotFoundException {
    PrintStream out = new PrintStream(membersCSV);
    saveMembersMethod(out, memberList.getList());
  }

  public ArrayList<Member> loadMemberList() throws FileNotFoundException {

    ArrayList<Member> newMemberList = new ArrayList<Member>();

    Scanner fileScanner = new Scanner(membersCSV);
    while (fileScanner.hasNextLine()){
      String line = fileScanner.nextLine();

      Scanner lineScanner = new Scanner(line).useDelimiter(";");

      String name = lineScanner.next();
      String memberID = lineScanner.next();
      String birthString = lineScanner.next();
      String phoneNumber = lineScanner.next();
      String email = lineScanner.next();
      String competitiveString = lineScanner.next();
      String arrearsString = lineScanner.next();
      String activeString = lineScanner.next();

      ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();

      double arrears = Double.parseDouble(arrearsString);
      LocalDate birth = LocalDate.parse(birthString);
      boolean competitive = competitiveString.equals("Kompetitiv");
      boolean active = activeString.equals("Aktivt");

      Member newMember;

      if (calculateIfJunior(birth)){
        newMember = new Junior(name, memberID, birth, phoneNumber, email, competitive, arrears, active);
      } else {
        newMember = new Senior(name, memberID, birth, phoneNumber, email, competitive, arrears, active);
      }

      newMemberList.add(newMember);

    }
      return newMemberList;
  }

  public int getMonthNumber(String month) {
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


  public boolean calculateIfJunior(LocalDate dateIn) {
    LocalDate now = LocalDate.now();
    LocalDate birthDate = LocalDate.of(dateIn.getYear(), dateIn.getMonth(), dateIn.getDayOfMonth());

    Period p = Period.between(birthDate, now);

    return p.getYears() >= 18;
  }


  public void saveMembersMethod(PrintStream printStream, ArrayList<Member> memberArrayList) {

    UserInterface ui = new UserInterface();

    ui.savingMessage();
    for (Member member : memberArrayList) {
      printStream.print(member.getName());
      printStream.print(";");
      printStream.print(member.getMemberID());
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
