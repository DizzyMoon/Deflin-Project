package members;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Member implements Comparable<Member> {

  private String name;
  private String memberID;
  private final String gender;
  private final LocalDate birth;
  private String phoneNumber;
  private String email;
  private boolean competitive;
  private double arrears;
  private boolean active;
  private ArrayList<Achievement> butterflyResults;
  private ArrayList<Achievement> crawlResults;
  private ArrayList<Achievement> backstrokeResults;
  private ArrayList<Achievement> breaststrokeResults;
  private Achievement[] tempTop3 = new Achievement[3];
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
  public Member(String name, String memberID, String gender, LocalDate birth, String phoneNumber, String email, boolean competitive, double arrears, boolean active) {
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

  public void setArrears(double arrears) {
    this.arrears = arrears;
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

  public double getArrears() {
    return arrears;
  }

  public ArrayList<Achievement> getButterflyResults() {
    return butterflyResults;
  }

  public void setButterflyResults(ArrayList<Achievement> butterflyResults) {
    this.butterflyResults = butterflyResults;
  }

  public ArrayList<Achievement> getCrawlResults() {
    return crawlResults;
  }

  public void setCrawlResults(ArrayList<Achievement> crawlResults) {
    this.crawlResults = crawlResults;
  }

  public ArrayList<Achievement> getBackcrawlResults() {
    return backstrokeResults;
  }

  public void setBackstrokeResults(ArrayList<Achievement> backstrokeResults) {
    this.backstrokeResults = backstrokeResults;
  }

  public ArrayList<Achievement> getBreaststrokeResults() {
    return breaststrokeResults;
  }

  public void setBreastStroke(ArrayList<Achievement> breaststrokeResults) {
    this.breaststrokeResults = breaststrokeResults;
  }

  public ArrayList getProficiency() {       //Search Arrays and find the most recent Achievements, return their list
    ///?????????
    ArrayList<Achievement> proficiency = new ArrayList(); //temporarily, new, should be this.xResults
    return proficiency;
  }

  public void setTempTop3(int i, Achievement achievement){
    this.tempTop3[i] = achievement;
  }

  public Achievement[] getTempTop3() {
    return tempTop3;
  }

  public String toString() {
    return name + ", " + birth + ", " + getCompetitive() + " - " + getActive();
  }

  @Override
  public int compareTo(Member o) {
    return this.name.compareTo(o.getName());
  }


}
