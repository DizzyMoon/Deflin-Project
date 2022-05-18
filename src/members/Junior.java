package members;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Junior extends Member{
  public Junior(String name, LocalDate birth, boolean competitive, boolean active) {
    super(name, birth, competitive, active);
  }
  public Junior(String name, String memberID, LocalDate birth, boolean competitive, double restance, boolean active, ArrayList<Achievement> butterflyResults, ArrayList<Achievement> crawlResults, ArrayList<Achievement> rygcrawlResults, ArrayList<Achievement> breastStrokeResults) {
    super(name, memberID, birth, competitive, restance, active, butterflyResults, crawlResults,rygcrawlResults,breastStrokeResults);
  }

  public Junior(String name, String memberID, LocalDate birth, boolean competitive, double restance, boolean active) {
    super(name, memberID, birth, competitive, restance,active);
  }

}
