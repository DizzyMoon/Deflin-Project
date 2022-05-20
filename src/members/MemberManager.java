package members;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

import UI.UserInterface;
import filehandling.FileHandler;
import members.Creator;

public class MemberManager {

  Creator cr = new Creator();
  private MemberList memberList = new MemberList();
  private FileHandler fileHandler = new FileHandler();
  private UserInterface ui = new UserInterface();

  public MemberManager() throws FileNotFoundException {
  }

  public void createNewMember(String name, LocalDate date, String phoneNumber, String email, boolean competition, boolean active) throws FileNotFoundException {
    LocalDate now = LocalDate.now();

    Period p = Period.between(date, now);

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

  public String giveUserID() {
    Random rnd = new Random();                // maybe a count++ and a check for MemberList.nextAvailiable()
    int number = rnd.nextInt(9999);
    return String.format("%04d", number);
  }

  public void createUserID(Member member) {
    String ID = giveUserID();
    member.setMemberID(ID);
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

  public void loadMembersFromCSV () throws FileNotFoundException {
    memberList.setList(cr.loadMembers());
  }

  public MemberList getMemberList() {
    return memberList;
  }

  public ArrayList<Member> getList() {
    return memberList.getList();
  }


}
