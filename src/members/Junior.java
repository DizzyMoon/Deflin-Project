package members;

import java.time.LocalDate;

public class Junior extends Member{

  public Junior(String name, String memberID, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean arrears, boolean active) {
    super(name, memberID, gender, birth, phoneNumber, email, competitive, arrears, active);
  }

}
