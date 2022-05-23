package UI;

import members.*;

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
        6 - Se top 5-liste
        7 - Tilbage til hovedmenu
        8 - Luk
        """);
  }

  public void top5AgeUI(){
    System.out.println("""
        1 - Se top5 SENIOR
        2 - Se top5 JUNIOR
        """);
  }

  public void top5GenderUI(){
    System.out.println("""
        1. Se top5 herrer
        2. Se top5 kvinder""");
  }

  public void top5StyleUI(){
    System.out.println("""
        1 - Backcrawl
        2 - Brystsvømning
        3 - Crawl
        4 - Butterfly""");
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

  public void badInput() {
    System.out.println("Forkert indtastning!");
  }

  public void memberName() {
    System.out.println("Opretter nyt medlem...\nNavn:");
  }

  public void gender() { System.out.println("Køn: (H/D)"); }

  public void dateOfBirth() {
    System.out.println("Indtast fødselsdato på nyt medlem (indtastes dd.mm.yyyy)");
  }

  public void savingMessage() {
    System.out.println("Gemmer...");
  }

  public void deleting() {
    System.out.println("Sletter...");
  }

  public void deleted() {
    System.out.println("Slettet!");
  }

  public void savedMessage() {
    System.out.println("Gemt!");
  }

  public void competitive() {
    System.out.println("Er det nye medlem konkurrencesvømmer(ja) eller ikke(nej)");
  }

  public void typeMemberIDForNameChange() {
    System.out.println("Indtast medlemsnummer for det medlem, hvis oplysninger du vil ændre:");
  }

  public void typeMemberIDForRemove() {
    System.out.println("Indtast medlemsnummer for det medlem, hvis medlemskab du vil slette");
  }

  public void nameChange() {
    System.out.println("Indtast nyt navn:");
  }

  public void phoneNumber() {
    System.out.println("Indtast mobilnummer på nyt medlem:");
  }

  public void email() {
    System.out.println("Indtast mailadresse:");
  }

  public void statusAltered(String newStatus) {
    System.out.println("Medlemsstatus er ændret til " + newStatus);
  }

  public void typeAltered(String newType) {
    System.out.println("Medlemsstatus er ændret til " + newType);
  }


  public void elementDoesNotExist() {
    System.out.println("Element findes ikke");
  }

  public void coachSchedule() {
    System.out.println("Feature kommer i næste udgave!");
  }

  public void promptEventName() {
    System.out.println("Event Titel:");
  }

  public void promptSubdivisionCode() {
    System.out.println("Division: (HJ / HS / DJ / DS)");
  }

  public void promptEventDate() { System.out.println("Dato: (dd.mm.yy)"); }

  public void promptEventTime() { System.out.println("Tidspunkt for træffet: (tt:mm)"); }

  public void listSwimmeets(ArrayList<Swimmeet> schedule) {
    System.out.println(schedule.toString());
  }

  public void addSwimmerToMeet() {
    System.out.println("");
  }

  public void inputSwimmerID() {
    System.out.println("Indtast svømmers medlemsnummer:");
  }

  public void inputDistance() {
    System.out.println("Konkurrence distance:");
  }

  public void inputTime() {
    System.out.println("Indtast opnået resultat: (mm:ss)");
  }

  public void addCommendation() {
    System.out.println("Har svømmeren opnået en udmærkelse? (ja/nej)");
  }

  public void commDescr() {
    System.out.println("1. Guld\t\t2. Sølv\t\t3.Bronze\t\t4.Andet");
  }

  public void writeDiscipline(){
    System.out.println("Indtast disciplin (butterfly, crawl, ryg, bryst)");
  }

  public void specialCommDescr() {
    System.out.println("Indtast navn på udmærkelse:");
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
          """, print.getMemberID(), print.getName(), print.getBirth().getDayOfMonth(), print.getBirth().getMonth(), print.getBirth().getYear(), print.getActive());

    }
  }

  public void printMemberListTable(MemberList ml) {
    String underLine = "-";
    String line = "|";
    String space = " ";
    String dot = ".";

    //Overskrifter
    System.out.println("Medlemsnr. | Navn" + space.repeat(26) + "| Medlemstype" + space.repeat(11) + "| Fødselsdato | Mobilnummer | E-mail");

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
      String age;
      if (print.getActiveBool()) {
        rep = 10;
      } else if (!print.getActiveBool()) {
        rep = 8;
      }
      if (ml.getList().get(i) instanceof Junior){
        age = "junior";
      }
      else if (ml.getList().get(i) instanceof Senior){
        age = "senior";
      }
      else{
        age = "fejl";
      }
      System.out.print(space + print.getActive() + space + age + space.repeat(rep) + line);

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

  public void printTop5(ArrayList<Member> member) {
    String underLine = "-";
    String line = "|";
    String space = " ";
    String dot = ".";
    String gender = "";
    if (member.get(0) instanceof Junior){
      if (member.get(0).getGender().equals("Mand")){
        gender = "herrer";
      }
      else if (member.get(0).getGender().equals("Kvinde")){
        gender = "kvinder";
      }
      System.out.println("Top 5 - JUNIOR " + gender);
    }
    else if (member.get(0) instanceof Senior){
      if (member.get(0).getGender().equals("Mand")){
        gender = "herrer";
      }
      else if (member.get(0).getGender().equals("Kvinde")){
        gender = "kvinder";
      }
      System.out.println("Top 5 - SENIOR " + gender);
    }

    System.out.println(" #   | Navn" + space.repeat(26) + line + " Bedste tid " + line + " Næstbedste tid " + line + " Tredjebedste tid " + line);
    //Linjeadskillelse
    System.out.println(underLine.repeat(128));

    for (int i = 0; i < 5; i++){

      //Placering
      System.out.print("Nr. " + i + space + line);

      //Navn
      System.out.print(member.get(i).getName() + space.repeat(31 - member.get(i).getName().length()) + line);

      //Svømmers bedste tid
      System.out.print(space + member.get(i).getCrawlResults().get(0) + space.repeat(5) + line);

      //Næstbedste tid
      System.out.print(space + member.get(i).getCrawlResults().get(1) + space.repeat(9) + line);

      //Tredjebedste tid
      System.out.print(space + member.get(i).getCrawlResults().get(2) + space.repeat(11) + line);

      //Linjeadskillelse
      System.out.println(underLine.repeat(128));
    }
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



