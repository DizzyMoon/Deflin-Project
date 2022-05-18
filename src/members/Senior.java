package members;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Senior extends Member {
  public Senior(String name, LocalDate birth, boolean competitive, boolean active) {
    super(name, birth, competitive, active);
  }
  public Senior(String name, String memberID, LocalDate birth, boolean competitive, double restance, boolean active, ArrayList<Achievement> butterflyResults, ArrayList<Achievement> crawlResults, ArrayList<Achievement> rygcrawlResults, ArrayList<Achievement> breastStrokeResults) {
    super(name, memberID, birth, competitive, restance, active, butterflyResults, crawlResults,rygcrawlResults,breastStrokeResults);
  }

  public Senior(String name, String memberID, LocalDate birth, boolean competitive, double restance, boolean active) {
    super(name, memberID, birth, competitive, restance,active);
  }



}
