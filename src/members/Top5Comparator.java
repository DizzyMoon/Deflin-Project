package members;

import java.util.Comparator;

public class Top5Comparator implements Comparator<Member> {
  @Override
  public int compare(Member o1, Member o2) {
    return o1.getTempTop3()[0].compareTo(o2.getTempTop3()[0]);
  }
}
