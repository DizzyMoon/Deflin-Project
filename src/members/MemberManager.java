package members;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

import UI.UserInterface;
import filehandling.FileHandler;

public class MemberManager {

  Creator cr = new Creator();
  private MemberList memberList = new MemberList();
  private FileHandler fileHandler = new FileHandler();
  private UserInterface ui = new UserInterface();
  private ArrayList<Member> juniorList = new ArrayList<Member>();
  private ArrayList<Member> seniorList = new ArrayList<Member>();
  private ArrayList<Member> men = new ArrayList<Member>();
  private ArrayList<Member> women = new ArrayList<Member>();

  public MemberManager() throws FileNotFoundException {
  }

  public void createNewMember(String name, String tempID, String gender, LocalDate date, String phoneNumber, String email, boolean competition, boolean arrears, boolean active) throws FileNotFoundException {

    LocalDate now = LocalDate.now();
    Period p = Period.between(date, now);

    if (p.getYears() >= 18) {
      Senior newMemberSenior = new Senior(name, tempID, gender, date, phoneNumber, email, competition, arrears, active);
      createUserID(newMemberSenior);
      memberList.getList().add(newMemberSenior);
      fileHandler.saveMembersToCSV(memberList);
    } else {
      Junior newMemberJunior = new Junior(name, tempID, gender, date, phoneNumber, email, competition, arrears, active);
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
        ui.elementDoesNotExist();
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

  public ArrayList<Member> sortSenior(){
    //ArrayList<Member> seniorList = new ArrayList<Member>();
    for (int i = 0; i < memberList.getList().size(); i++) {
      if (memberList.getList().get(i) instanceof Senior){
        seniorList.add(memberList.getList().get(i));
      }
    }
    return seniorList;
  }

  public ArrayList<Member> sortJunior(){
    //ArrayList<Member> juniorList = new ArrayList<Member>();
    for (int i = 0; i < memberList.getList().size(); i++) {
      if (memberList.getList().get(i) instanceof Junior){
        juniorList.add(memberList.getList().get(i));
      }
    }
    return juniorList;
  }

  public void womensList(ArrayList<Member> member) {
    //ArrayList<Member> women = new ArrayList<>();
    for (int i = 0; i < member.size(); i++)
      if (member.get(i).getGender().equalsIgnoreCase("D")) {
        women.add(member.get(i));
      }
  }

  public void mensList(ArrayList<Member> member) {
    //ArrayList<Member> men = new ArrayList<>();
    for (int i = 0; i < member.size(); i++)
      if (member.get(i).getGender().equalsIgnoreCase("H")) {
        men.add(member.get(i));
      }
  }

  public ArrayList<Member> getMen() {
    return men;
  }

  public ArrayList<Member> getWomen() {
    return women;
  }

  public ArrayList<Member> getJuniorList() {
    return juniorList;
  }

  public ArrayList<Member> getSeniorList() {
    return seniorList;
  }
}