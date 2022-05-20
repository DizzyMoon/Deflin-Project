package members;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import filehandling.FileHandler;

public class Creator {
  private MemberList memberList = new MemberList();
  private FileHandler fileHandler = new FileHandler();

  public Creator() throws FileNotFoundException {
  }

  public ArrayList<Member> loadMembers() throws FileNotFoundException{
    return fileHandler.loadMemberList();
  }
}
