package members;

import Achievement.Achievement;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Member implements Comparable<Member> {

  private String name;
  private String memberID;
  private final String gender;
  private final LocalDate birth;
  private String phoneNumber;
  private String email;
  private boolean competitive;
  private boolean arrears;
  private boolean active;
  private ArrayList<Achievement> butterflyResults = new ArrayList<>();
  private ArrayList<Achievement> crawlResults = new ArrayList<>();
  private ArrayList<Achievement> backstrokeResults = new ArrayList<>();
  private ArrayList<Achievement> breaststrokeResults = new ArrayList<>();
  private ArrayList<Achievement> tempTop3 = new ArrayList<>();

/*
  public Member(String name, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean active) {
    this.name = name;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.active = active;
    this.birth = birth;
    this.competitive = competitive;
  }
*/
  public Member(String name, String memberID, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, boolean arrears, boolean active) {
    this.name = name;
    this.memberID = memberID;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.active = active;
    this.birth = birth;
    this.competitive = competitive;
    this.arrears = arrears;
  }

  public String getGender() { return gender; }

  public String getActive() {
    return this.active ? "Aktiv" : "Inaktiv";
  }   // Skal i UI-klasse?

  public boolean getActiveBool() {
    return this.active;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setMemberID(String memberID) {

    this.memberID = memberID;
  }

  public LocalDate getBirth() {
    return birth;
  }

  public void toggleCompetitive() {
    this.competitive = !competitive;
  }

  public void toggleStatus() { this.active = !active; }

  public String getArrears() {
    return this.arrears ? "Betalt" : "Ikke betalt";
  }

  public void toggleArrears() { this.arrears = !arrears; }

  public boolean getArrearsBool () {
    return this.arrears;
  }

  public String getName() {
    return name;
  }

  public String getMemberID() {
    return memberID;
  }

  public String getCompetitive() {
    return competitive ? "Konkurrencesvømmer" : "Motionist";
  }

  public ArrayList<Achievement> getButterflyResults() {
    return butterflyResults;
  }

  public void setButterflyResults(Achievement butterflyResults) {
    this.butterflyResults.add(butterflyResults);
  }

  public ArrayList<Achievement> getCrawlResults() {
    return crawlResults;
  }

  public void setCrawlResults(Achievement crawlResults) {
    this.crawlResults.add(crawlResults);
  }

  public ArrayList<Achievement> getBackstrokeResults() {
    return backstrokeResults;
  }

  public void setBackstrokeResults(Achievement backstrokeResults) {
    this.backstrokeResults.add(backstrokeResults);
  }

  public ArrayList<Achievement> getBreaststrokeResults() {
    return breaststrokeResults;
  }

  public void setBreastStroke(Achievement breaststrokeResults) {
    this.breaststrokeResults.add(breaststrokeResults);
  }

  public void setTempTop3(Achievement achievement){
    this.tempTop3.add(achievement);
  }

  public ArrayList<Achievement> getTempTop3() {
    return tempTop3;
  }

  public String toString() {
    return name + ", " + birth + ", " + getCompetitive() + " - " + getActive() + " - " + getArrears();
  }

  @Override
  public int compareTo(Member o) {
    return this.name.compareTo(o.getName());
  }


}
