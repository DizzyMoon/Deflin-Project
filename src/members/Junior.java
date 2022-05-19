package members;

import java.time.LocalDate;

public class Junior extends Member{
  public Junior(String name, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean active) {
    super(name, birth, phoneNumber, email, competitive, active);
  }

  public Junior(String name, String memberID, LocalDate birth, String phoneNumber, String email, boolean competitive, double arrears, boolean active) {
    super(name, memberID, birth, phoneNumber, email, competitive, arrears, active);
  }

}
