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
  private FileHandler fileHandler = new FileHandler();

  public Creator() throws FileNotFoundException {
  }

  public ArrayList<Member> loadMembers() throws FileNotFoundException{
    return fileHandler.loadMemberList();
  }

}
