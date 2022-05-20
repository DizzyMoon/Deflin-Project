package members;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import UI.UserInterface;

import members.Junior;
import members.MemberList;
import members.Member;
import members.Senior;
import filehandling.FileHandler;

public class Creator {
  private MemberList memberList = new MemberList();
  private FileHandler fileHandler = new FileHandler();
  private UserInterface ui = new UserInterface();

  public Creator() throws FileNotFoundException {
  }

  public ArrayList<Member> loadMembers() throws FileNotFoundException{
    return fileHandler.loadMemberList();
  }

  public ArrayList<Member> sortSenior(){
    ArrayList<Member> seniorList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (memberList.getList().get(i) instanceof Senior){
        seniorList.add(memberList.getList().get(i));
      }
    }
    return seniorList;
  }

  public ArrayList<Member> sortJunior(){
    ArrayList<Member> juniorList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (memberList.getList().get(i) instanceof Senior){
        juniorList.add(memberList.getList().get(i));
      }
    }
    return juniorList;
  }
}
