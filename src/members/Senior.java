package members;

import java.time.LocalDate;
import java.util.Date;

public class Senior extends Member {
  public Senior(String name, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean active) {
    super(name, birth, phoneNumber, email, competitive, active);
  }

  public Senior(String name, String memberID, LocalDate birth, String phoneNumber, String email, boolean competitive, double arrears, boolean active) {
    super(name, memberID, birth, phoneNumber, email, competitive, arrears, active);
  }
}
