package members;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Member implements Comparable<Member> {

  private String name;
  private String memberID;
  private final Date birth;
  private String phoneNumber;
  private String email;
  private boolean competitive;
  private double restance;
  private boolean active;
  private ArrayList<Result> butterflyResults;
  private ArrayList<Result> crawlResults;
  private ArrayList<Result> rygcrawlResults;
  private ArrayList<Result> breastStrokeResults;


  public Member(String name, Date birth, String phoneNumber, String email, boolean competitive, boolean active) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.active = active;
    this.birth = birth;
    this.competitive = competitive;
  }


  public String getActive() {
    return this.active ? "Aktivt" : "Inaktivt";
  }

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

  public Date getBirth() {
    return birth;
  }

  public void setCompetitive(boolean competitive) {
    this.competitive = competitive;
  }

  public void setRestance(double restance) {
    this.restance = restance;
  }

  public String getName() {
    return name;
  }

  public String getMemberID() {
    return memberID;
  }

  public String getCompetitive() {
    return competitive ? "Competitive member" : "Exercise member";
  }

  public double getRestance() {
    return restance;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public ArrayList<Result> getButterflyResults() {
    return butterflyResults;
  }

  public void setButterflyResults(ArrayList<Result> butterflyResults) {
    this.butterflyResults = butterflyResults;
  }

  public ArrayList<Result> getCrawlResults() {
    return crawlResults;
  }

  public void setCrawlResults(ArrayList<Result> crawlResults) {
    this.crawlResults = crawlResults;
  }

  public ArrayList<Result> getRygcrawlResults() {
    return rygcrawlResults;
  }

  public void setRygcrawlResults(ArrayList<Result> rygcrawlResults) {
    this.rygcrawlResults = rygcrawlResults;
  }

  public ArrayList<Result> getBreastStrokeResults() {
    return breastStrokeResults;
  }

  public void setBreastStroke(ArrayList<Result> breastStroke) {
    this.breastStrokeResults = breastStroke;
  }

  public String toString() {
    return name + ", " + birth + ", " + getCompetitive() + " - " + getActive();
  }

  @Override
  public int compareTo(Member o) {
    return this.name.compareTo(o.getName());
  }
}
