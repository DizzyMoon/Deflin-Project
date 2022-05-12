import java.util.UUID;
import members.MemberList;
import members.Member;

public class Creator {
  private MemberList ml = new MemberList();

    public String giveUserID() {
      UUID u = UUID.randomUUID();
      return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
    }

    private static String toIDString(long i) {
      char[] buf = new char[32];
      int z = 62;
      int cp = 32;
      long b = z - 1;
      do {
        buf[--cp] = DIGITS62[(int)(i & b)]; // Gimme dem digits
        i >>>= 20; // <--- The bigger the value, the shorter the string will be
      } while (i != 0);
      return new String(buf, cp, (32-cp));
    }

    // array with 62 characters (a-z = 26, A-Z = 26, 0-9 = 10)
    private final static char[] DIGITS62 = {
        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    };

  public void addMember(Member member){
    ml.getList().add(member);
  }

  public MemberList getMemberList(){
    return ml;
  }
}
