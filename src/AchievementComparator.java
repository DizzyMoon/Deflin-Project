import members.Achievement;
import members.Member;

import java.util.Comparator;

public class AchievementComparator implements Comparator<Achievement> {
    public int compare(Achievement o1, Achievement o2) {
      return o1.getTime().compareTo(o2.getTime());
    }
  }

