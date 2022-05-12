import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;

import members.Junior;
import members.Member;
import members.Senior;

public class Creator {

  public void createNewMember(String name, Date date, boolean competition, boolean active){
    LocalDate now = LocalDate.now();

    LocalDate birthDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    Period p = Period.between(birthDate, now);

    if(p.getYears() >= 18) {
      Member newMember = new Senior(name, date, competition, active);
    }
    else {
      Member newMember = new Junior(name, date, competition, active);
    }
  }


}
