package members;

import java.time.LocalDate;

public class Senior extends Member {
  boolean retired = false;

  public Senior(String name, String memberID, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean arrears, boolean active) {
    super(name, memberID, gender, birth, phoneNumber, email, competitive, arrears, active);
  }

  public void setRetired() {
    this.retired = true;
  }
}
