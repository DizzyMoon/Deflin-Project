package members;

import java.util.Comparator;

public class ArrearsComparator implements Comparator<Member> {
  @Override
  public int compare(Member o1, Member o2) {
    return o2.getArrears().compareTo(o1.getArrears());
  }
}
