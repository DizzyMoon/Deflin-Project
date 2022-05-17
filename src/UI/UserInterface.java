package UI;

import members.Member;
import members.MemberList;


public class UserInterface {

  public void startupMenu() {
    System.out.println("""
        1 - Formand
        2 - Kasserer
        3 - Træner
        4 - Luk
        \n""");
  }

  public void formandUI() {
    System.out.println("""
        1 - Opret nyt medlem
        2 - Slet medlemskab
        3 - Ændre medlemskab til passiv/aktiv
        4 - Ændre navn på medlem
        5 - Se medlemsliste
        6 - Tilbage til hovedmenu
        7 - Luk
        \n""");
  }

  public void kassererUI() {
    System.out.println("""
        1 - Se samlet sum af medlemmers årskontingenter
        2 - Se liste over restancer
        3 - Tilbage til hovedmenu
        4 - Luk
        """);
  }

  public void memberName() {
    System.out.println("Indtast navn på nyt medlem");
  }

  public void dateOfBirth() {
    System.out.println("Indtast fødselsdato på nyt medlem (indtastes dd.mm.yyyy)");
  }

  public void competetive() {
    System.out.println("Er det nye medlem konkurrencesvømmer(ja) eller ikke(nej)");
  }

  public void typeMemberID() {
    System.out.println("Indtast medlemsnummer på person hvis navn du vil ændre");
  }

  public void nameChange() {
    System.out.println("Indtast det ændrede navn");
  }

  public void phoneNumber(){
    System.out.println("Indtast mobilnummer på nyt medlem");
  }

  public void email(){
    System.out.println("Indtast mailadresse for nyt medlem");
  }

  public void printMemberList(MemberList ml) {

    for (int i = 0; i < ml.getList().size(); i++) {
      Member print = ml.getList().get(i);

      System.out.printf("""
          Medlemsnummer: %s
          Navn: %s
          Fødselsdato: %s/%s, %s
          Aktivt medlem: %b
          \n
          """, print.getMemberID(), print.getName(), print.getBirth().getDate(), print.getBirth().getMonth(), print.getBirth().getYear(), print.getActive());

    }
  }

  public void printMemberListTable(MemberList ml) {
    String underLine = "-";
    String line = "|";
    String space = " ";
    String dot = ".";

    //Overskrifter
    System.out.println("Medlemsnr. | Navn" + space.repeat(16) + "| Medlemstype | Fødselsdato | Mobilnummer | E-mail");

    //Linjeadskillelse
    System.out.println(underLine.repeat(100));

    for (int i = 0; i < ml.getList().size(); i++) {
      Member print = ml.getList().get(i);

      //Medlemsnummer
      System.out.print(space + print.getMemberID() + space.repeat(6) + line);

      //Navn
      System.out.print(space + print.getName() + space.repeat(20 - print.getName().length()) + line);

      //Medlemstype
      int rep = 0;
      if (print.getActiveBool()) {
        rep = 6;
      } else if (!print.getActiveBool()) {
        rep = 4;
      }
      System.out.print(space + print.getActive() + space.repeat(rep) + line);

      //Fødselsdato
      String birthday = Integer.toString(print.getBirth().getDate()) + dot + Integer.toString(print.getBirth().getMonth()) + dot + Integer.toString(print.getBirth().getYear());
      System.out.print(space + birthday + space.repeat(12 - birthday.length()) + line);

      //Telefonnummer
      System.out.print(space + print.getPhoneNumber() + space.repeat(4) + line);

      //Mailadresse
      System.out.println(space + print.getEmail());

      //Linjeadskillelse
      System.out.println(underLine.repeat(100) + "\n");
    }
  }
}



