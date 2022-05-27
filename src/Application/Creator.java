package Application;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Achievement.Achievement;
import UI.UserInterface;
import users.*;

import event.EventList;
import event.Swimmeet;
import filehandling.FileHandler;
import members.Member;
import members.MemberList;

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

  public ArrayList<Achievement> loadAchievements() throws FileNotFoundException{
    return fileHandler.achievementListLoad();
  }

  public Swimmeet createNewEvent(String eventName, String category, boolean league, LocalDateTime eventTime) {
    Swimmeet nextMeet = new Swimmeet(eventName, category, league, eventTime);
    return nextMeet;
  }
}

