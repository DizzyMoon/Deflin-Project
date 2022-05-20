package members;

import java.time.LocalDate;

public class Junior extends Member{
  public Junior(String name, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean active) {
    super(name, gender, birth, phoneNumber, email, competitive, active);
  }

  public Junior(String name, String memberID, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, double arrears, boolean active) {
    super(name, memberID, gender, birth, phoneNumber, email, competitive, arrears, active);
  }

}
