package UI;

import members.EventList;
import members.Member;
import members.MemberList;
import members.Swimmeet;

import java.util.ArrayList;

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
        4 - Ændre type for konkurrence/motionist
        5 - Ændre navn på medlem
        6 - Se medlemsliste
        7 - Tilbage til hovedmenu
        8 - Luk
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

  public void traenerUI() {
    System.out.println("""
        1 - Se ugeskema
        2 - Opret stævne
        3 - Se stævner
        4 - Udtag svømmere til stævne
        5 - Bogfør præstation
        6 - Tilbage til hovedmenu
        7 - Luk
        """);
  }
  /*
  public void infoConsoleUI() {
    System.out.println("""
        1 - Klub Info
        2 - Se ugeskema
        3 - Se Event kalender og stævner
        4 - Rygcrawl      TOP 5 bedste svømmere
        5 - Brystsvømning TOP 5 bedste svømmere
        6 - Butterfly     TOP 5 bedste svømmere
        7 - Crawl         TOP 5 bedste svømmere
        """);
  }
  */

  public void badInput() { System.out.println("Forkert indtastning!"); }

  public void memberName() {
    System.out.println("Opretter nyt medlem.../nNavn:");
  }

  public void dateOfBirth() {
    System.out.println("Indtast fødselsdato på nyt medlem (indtastes dd.mm.yyyy)");
  }

  public void competetive() {
    System.out.println("Er det nye medlem konkurrencesvømmer? (ja/nej)");
  }

  public void typeMemberID() { System.out.println("Indtast medlemsnummer for det medlem, hvis oplysninger du vil ændre:"); }

  public void nameChange() {
    System.out.println("Indtast nyt navn:");
  }

  public void phoneNumber(){
    System.out.println("Indtast mobilnummer på nyt medlem:");
  }

  public void email(){
    System.out.println("Indtast mailadresse:");
  }

  public void statusAltered(String newStatus) { System.out.println("Medlemsstatus er ændret til " + newStatus); }

  public void typeAltered(String newType) { System.out.println("Medlemsstatus er ændret til " + newType); }


  public void coachSchedule() { System.out.println("Feature kommer i næste udgave!"); }

  public void planSwimmeet() { System.out.println("Event Titel:"); }

  public void listSwimmeets(ArrayList<Swimmeet> schedule) { System.out.println(schedule.toString()); }

  public void addSwimmerToMeet() {
    System.out.println( "");
  }

  public void inputSwimmerID() { System.out.println("Indtast svømmers medlemsnummer:"); }

  public void inputDistance() { System.out.println("Indtast svømmers medlemsnummer:"); }

  public void inputTime() { System.out.println("Indtast svømmers medlemsnummer:"); }

  public void addCommendation() { System.out.println("Har svømmeren opnået en udmærkelse? (ja/nej)"); }

  public void commDescr() { System.out.println("1. Guld\t\t2. Sølv\t\t3.Bronze\t\t4.Andet"); }

  public void specialCommDescr() { System.out.println("Indtast navn på udmærkelse:"); }


  public void printMemberList(MemberList ml) {

    for (int i = 0; i < ml.getList().size(); i++) {
      Member print = ml.getList().get(i);

      System.out.printf("""
          Medlemsnummer: %s
          Navn: %s
          Fødselsdato: %s/%s, %s
          Aktivt medlem: %b
          \n
          """, print.getMemberID(), print.getName(), print.getBirth().getDayOfMonth(), print.getBirth().getMonth(), print.getBirth().getYear(), print.getActive());

    }
  }

  public void printMemberListTable(MemberList ml) {
    String underLine = "-";
    String line = "|";
    String space = " ";
    String dot = ".";

    //Overskrifter
    System.out.println("Medlemsnr. | Navn" + space.repeat(26) + "| Medlemstype" + space.repeat(17) + "| Fødselsdato | Mobilnummer | E-mail");

    //Linjeadskillelse
    System.out.println(underLine.repeat(128));

    for (int i = 0; i < ml.getList().size(); i++) {
      Member print = ml.getList().get(i);

      //Medlemsnummer
      System.out.print(space + print.getMemberID() + space.repeat(6) + line);

      //Navn
      System.out.print(space + print.getName() + space.repeat(30 - print.getName().length()) + line);

      //Medlemstype             // returner (+ evt. Konkurrence) Junior/Senior, eller Passiv
      int rep = 0;
      if (print.getActiveBool()) {
        rep = 22;
      } else if (!print.getActiveBool()) {
        rep = 20;
      }
      System.out.print(space + print.getActive() + space.repeat(rep) + line);

      //Fødselsdato
      String birthday = Integer.toString(print.getBirth().getDayOfMonth()) + dot + Integer.toString(print.getBirth().getMonthValue()) + dot + Integer.toString(print.getBirth().getYear());
      System.out.print(space + birthday + space.repeat(12 - birthday.length()) + line);

      //Telefonnummer
      System.out.print(space + print.getPhoneNumber() + space.repeat(12 - print.getPhoneNumber().length()) + line);

      //Mailadresse
      System.out.println(space + print.getEmail());

      //Linjeadskillelse
      System.out.println(underLine.repeat(128));
    }
    //Ekstra linjeskift efter sidste linje i tabel
    System.out.println();
  }
/*
  public void printEventListTable(EventList meets) {
    String line = "|";
    //String dot = ".";

    System.out.printf("%10d %1s %-42s %1s %", eventName, line, eventDate, line, eventType);

    for (int i = 0; i < meets.getList().size(); i++) {
      Swimmeet print = meets.getList().get(i);

    }
  }
*/
}



