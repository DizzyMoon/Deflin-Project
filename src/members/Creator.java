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
      Random rnd = new Random();
      int number = rnd.nextInt(9999);
      return String.format("%04d", number);
    }

    public void createUserID(Member member){
      String ID = giveUserID();
      member.setMemberID(ID);
    }

  public void createNewMember(String name, Date date, boolean competition, boolean active) {
    LocalDate now = LocalDate.now();

    LocalDate birthDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    Period p = Period.between(birthDate, now);

    if (p.getYears() >= 18) {
      Member newMemberSenior = new Senior(name, date, competition, active);
      createUserID(newMemberSenior);
      memberList.getList().add(newMemberSenior);
      fileHandler.saveMembersToCSV(memberList);
    } else {
      Member newMemberJunior = new Junior(name, date, competition, active);
      createUserID(newMemberJunior);
      memberList.getList().add(newMemberJunior);
      fileHandler.saveMembersToCSV(memberList);
    }
  }


  private static String toIDString(long i) {
    char[] buf = new char[32];
    int z = 62;
    int cp = 32;
    long b = z - 1;
    do {
      buf[--cp] = DIGITS62[(int) (i & b)]; // Gimme dem digits
      i >>>= 20; // <--- The bigger the value, the shorter the string will be
    } while (i != 0);
    return new String(buf, cp, (32 - cp));
  }

  // array with 62 characters (a-z = 26, A-Z = 26, 0-9 = 10)
  private final static char[] DIGITS62 = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
  };
  public void addMember(Member member){
    memberList.getList().add(member);
  }

  public MemberList getMemberList(){
    return memberList;
  }
}
