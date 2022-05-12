import members.MemberList;

import members.Member;

public class Creator {
  private MemberList ml = new MemberList();

  public void addMember(Member member){
    ml.getList().add(member);
  }

  public MemberList getMemberList(){
    return ml;
  }
}
