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
        6 - Luk
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

  public void typeMemberID(){
    System.out.println("Indtast medlemsnummer på person hvis navn du vil ændre");
  }

  public void nameChange(){
    System.out.println("Indtast det ændrede navn");
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
}

