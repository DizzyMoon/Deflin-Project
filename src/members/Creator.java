package members;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;
import UI.UserInterface;

import filehandling.FileHandler;

public class Creator {
  private MemberList memberList = new MemberList();
  private EventList eventList = new EventList();
  private FileHandler fileHandler = new FileHandler();
  private UserInterface ui = new UserInterface();

  public Creator() throws FileNotFoundException {
  }

  public ArrayList<Member> loadMembers() throws FileNotFoundException{
    return fileHandler.loadMemberList();
  }

/*  public void loadAchivements() throws FileNotFoundException{
    fileHandler.loadAchievementList();
  }*/


/*  public void createNewMember(String name, String gender, LocalDate date, String phoneNumber, String email, boolean competition, boolean active) throws FileNotFoundException {
    String tempID = " ";
    double noArrears = 0;

    LocalDate now = LocalDate.now();
    Period p = Period.between(date, now);

    if (p.getYears() >= 18) {
      Member newMemberSenior = new Senior(name, tempID, gender, date, phoneNumber, email, competition, noArrears, active);
      createUserID(newMemberSenior);
      memberList.getList().add(newMemberSenior);
      fileHandler.saveMembersToCSV(memberList);
    } else {
      Member newMemberJunior = new Junior(name, tempID, gender, date, phoneNumber, email, competition, noArrears, active);
      createUserID(newMemberJunior);
      memberList.getList().add(newMemberJunior);
      fileHandler.saveMembersToCSV(memberList);

    }
  }

  public void addMember(Member member) {      // Replaced with getList().add
    memberList.getList().add(member);
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
        ui.elementDoesNotExist();
      }
    }

  }

  public MemberList getMemberList() {
    return memberList;
  }

  public ArrayList<Member> getList() {
    return memberList.getList();
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
  */
  public Swimmeet createNewEvent(String eventName, String category, boolean league, LocalDateTime eventTime) {
    Swimmeet nextMeet = new Swimmeet(eventName, category, league, eventTime);
    return nextMeet;
  }
}

