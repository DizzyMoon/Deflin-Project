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

  public String giveUserID() {
    Random rnd = new Random();                // maybe a count++ and a check for MemberList.nextAvailiable()
    int number = rnd.nextInt(9999);
    return String.format("%04d", number);
  }

  public void createUserID(Member member) {
    String ID = giveUserID();
    member.setMemberID(ID);
  }

  public void loadMembers() throws FileNotFoundException{
    memberList.setList(fileHandler.loadMemberList());
  }

  public void createNewMember(String name, LocalDate date, String phoneNumber, String email, boolean competition, boolean active) throws FileNotFoundException {
    LocalDate now = LocalDate.now();

    LocalDate birthDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
    Period p = Period.between(birthDate, now);

    if (p.getYears() >= 18) {
      Member newMemberSenior = new Senior(name, date, phoneNumber, email, competition, active);
      createUserID(newMemberSenior);
      memberList.getList().add(newMemberSenior);
      fileHandler.saveMembersToCSV(memberList);
    } else {
      Member newMemberJunior = new Junior(name, date, phoneNumber, email, competition, active);
      createUserID(newMemberJunior);
      memberList.getList().add(newMemberJunior);
      fileHandler.saveMembersToCSV(memberList);

    }
  }

  public void removeMember (String UID) throws FileNotFoundException {
    String memberID = UID;
    boolean found = false;
    int searchedElements = 0;

    for (Member member : memberList.getList()){
      if (member.getMemberID().equals(UID)){
        found = true;
        ui.deleting();
        memberList.getList().remove(member);
        ui.deleted();
        fileHandler.saveMembersToCSV(memberList);
        break;
      }
      searchedElements++;
      if (searchedElements == memberList.getList().size() && !found){
        ui.elementDoesNotExits();
      }
    }

  }

  public void addMember(Member member) {
    memberList.getList().add(member);
  }

  public MemberList getMemberList() {
    return memberList;
  }

  public ArrayList<Member> getList() {
    return memberList.getList();
  }
}
