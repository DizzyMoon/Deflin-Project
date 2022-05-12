package UI;

public class UserInterface {

  public void startupMenu(){
    System.out.println("""
        1 - Formand
        2 - Kasserer
        3 - Træner
        4 - Luk
        \n""");
  }

  public void formandUI(){
    System.out.println("""
       1 - Opret nyt medlem
       2 - Slet medlemskab
       3 - Ændre medlemskab til passiv/aktiv
       4 - Luk
       \n""");
  }

  public void memberName(){
    System.out.println("Indtast navn på nyt medlem");
  }

  public void dayOfBirth(){
    System.out.println("Indtast dag på måneden nyt medlem er født");
  }

  public void monthOfBirth(){
    System.out.println("Indtast måned (tal) nyt medlem er født");
  }

  public void yearOfBirth(){
    System.out.println("Indtast årstal nyt medlem er født");
  }

  public void competetive(){
    System.out.println("Er det nye medlem konkurrencesvømmer(ja) eller ikke(nej)");
  }
}
