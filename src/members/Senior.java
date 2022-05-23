package members;

import java.time.LocalDate;

public class Senior extends Member {
  boolean retired = false;
/*  public Senior(String name, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean active) {
    super(name, gender, birth, phoneNumber, email, competitive, active);
  }
*/
  public Senior(String name, String gender, String memberID, LocalDate birth, String phoneNumber, String email, boolean competitive, double arrears, boolean active) {
    super(name, memberID, gender, birth, phoneNumber, email, competitive, arrears, active);
  }

  public void setRetired() {
    this.retired = true;
  }
}
