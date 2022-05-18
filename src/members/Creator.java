package members;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import members.Junior;
import members.MemberList;
import members.Member;
import members.Senior;
import filehandling.FileHandler;

public class Creator {
  private MemberList memberList = new MemberList();
  private FileHandler fileHandler = new FileHandler();

  public Creator() throws FileNotFoundException {
  }

    public String giveUserID() {
      Random rnd = new Random();                // maybe a count++ and a check for MemberList.nextAvailiable()
      int number = rnd.nextInt(9999);
      return String.format("%04d", number);
    }

    public void createUserID(Member member){
      String ID = giveUserID();
      member.setMemberID(ID);
    }

  public void createNewMember(String name, Date date, String phoneNumber, String email, boolean competition, boolean active) {
    LocalDate now = LocalDate.now();

    LocalDate birthDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
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

  public void addMember(Member member){
    memberList.getList().add(member);
  }

  public MemberList getMemberList(){
    return memberList;
  }
}
