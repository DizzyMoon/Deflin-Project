package members;

import java.util.ArrayList;

public class MemberList {
  private ArrayList<Member> list = new ArrayList<>();
  StringBuilder sb = new StringBuilder();

  public ArrayList<Member> getList() {
    return list;
  }

  public String toString() {
    for (Member member : list) {
      sb.append(member + "\n");

    }
    return String.valueOf(sb);
  }
}