package members;

import java.util.Comparator;

public class ArrearsComparator implements Comparator<Member> {
  @Override
  public int compare(Member o1, Member o2) {
    return o1.getArrears().compareTo(o2.getArrears());
  }
}
