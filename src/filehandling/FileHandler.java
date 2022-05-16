package filehandling;

import members.Member;
import members.MemberList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
  Scanner sc = new Scanner(System.in);
  boolean running = true;
  private PrintStream out = new PrintStream(new File("members.csv"));

  public void saveMembersToCSV(MemberList memberList){
    saveMembersMethod(out, memberList.getList());
  }

  public FileHandler() throws FileNotFoundException {
  }

  public void saveMembersMethod(PrintStream printStream, ArrayList<Member> memberArrayList) {
    System.out.println("Saving...");
    for (Member member : memberArrayList) {
      printStream.print(member.getName());
      printStream.print(";");
      printStream.print(member.getMemberID());
      printStream.print(";");
      printStream.print(member.getBirth());
      printStream.print(";");
      printStream.print(member.getRestance());
      printStream.print(";");
      printStream.print(member.getActive());
      printStream.print(";");
      printStream.print(member.getButterflyResults());
      printStream.print(";");
      printStream.print(member.getCrawlResults());
      printStream.print(";");
      printStream.print(member.getRygcrawlResults());
      printStream.print(";");
      printStream.print(member.getBreastStrokeResults());
      printStream.print("\n");
    }
    System.out.println("Saved!");
  }
}
