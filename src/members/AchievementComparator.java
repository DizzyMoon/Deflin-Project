package members;

import members.Achievement;
import members.Member;

import java.util.Comparator;

public class AchievementComparator implements Comparator<Achievement> {
  public int compare(Achievement o1, Achievement o2) {
    if (o1.getTime().getMinute() == o2.getTime().getMinute()) {
      if (o1.getTime().getSecond() < o2.getTime().getSecond()) {
        return o2.getTime().getSecond();
      } else {
        return o1.getTime().getSecond();
      }
    } else if (o1.getTime().getMinute() > o2.getTime().getMinute()) {
      return o1.getTime().getSecond();
    } else {
      return o2.getTime().getSecond();
    }
  }
}

