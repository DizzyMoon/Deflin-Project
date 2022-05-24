package members;

import java.util.Comparator;

public class Top5Comparator implements Comparator<Member> {
  @Override
  public int compare(Member o1, Member o2) {
    if (o1.getTempTop3().get(0).getTime().getMinute() == o2.getTempTop3().get(0).getTime().getMinute()) {
      if (o1.getTempTop3().get(0).getTime().getSecond() < o2.getTempTop3().get(0).getTime().getSecond()) {
        return o1.getTempTop3().get(0).getTime().getSecond();
      } else {
        return o2.getTempTop3().get(0).getTime().getSecond();
      }
    } else if (o1.getTempTop3().get(0).getTime().getMinute() > o2.getTempTop3().get(0).getTime().getMinute()) {
      return o2.getTempTop3().get(0).getTime().getSecond();
    } else {
      return o1.getTempTop3().get(0).getTime().getSecond();
    }
  }

    //return o1.getTempTop3().get(0).compareTo(o2.getTempTop3().get(0));
  }

