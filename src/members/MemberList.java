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

  public Member findMember(String userID, MemberList memberList) {
    for (int i = 0; i < memberList.getList().size(); i++) {
      if (memberList.getList().get(i).getMemberID().equals(userID))
        return memberList.getList().get(i);
    }
    return null;
  }
}