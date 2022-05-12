import java.util.Date;

public class Creator {

  public void createNewMember(String name, Date date, boolean competition, boolean active){


    Member newMember = new Member(name, date, competition, active);

  }
}
