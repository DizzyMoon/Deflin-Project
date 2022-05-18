package members;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class Member {

  private String name;
  private String memberID;
  private LocalDate birth;
  private boolean competitive;
  private double restance;
  private boolean active;
  private ArrayList<Achievement> butterflyResults;
  private ArrayList<Achievement> crawlResults;
  private ArrayList<Achievement> rygcrawlResults;
  private ArrayList<Achievement> breastStrokeResults;


  public Member(String name, LocalDate birth, boolean competitive, boolean active) {
    this.name = name;
    this.active = active;
    this.birth = birth;
    this.competitive = competitive;
  }

  public Member(String name, String memberID, LocalDate birth, boolean competitive,
                double restance, boolean active, ArrayList<Achievement> butterflyResults,
                ArrayList<Achievement> crawlResults, ArrayList<Achievement> rygcrawlResults,
                ArrayList<Achievement> breastStrokeResults) {
    this.name = name;
    this.memberID = memberID;
    this.birth = birth;
    this.competitive = competitive;
    this.restance = restance;
    this.active = active;
    this.butterflyResults = butterflyResults;
    this.crawlResults = crawlResults;
    this.rygcrawlResults = rygcrawlResults;
    this.breastStrokeResults = breastStrokeResults;

  }

  public Member(String name, String memberID, LocalDate birth, boolean competitive, double restance, boolean active) {
    this.name = name;
    this.memberID = memberID;
    this.birth = birth;
    this.competitive = competitive;
    this.restance = restance;
    this.active = active;
  }


  public String getActive() {
    return this.active ? "Member_is_active" : "Member_is_not_active";
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMemberID(String memberID) {

    this.memberID = memberID;
  }

  public LocalDate getBirth() {
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
    return competitive ? "Competitive_member" : "Exercise_member";
  }

  public double getRestance() {
    return restance;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public ArrayList<Achievement> getRygcrawlResults() {
    return rygcrawlResults;
  }

  public void setRygcrawlResults(ArrayList<Achievement> rygcrawlResults) {
    this.rygcrawlResults = rygcrawlResults;
  }

  public ArrayList<Achievement> getBreastStrokeResults() {
    return breastStrokeResults;
  }

  public void setBreastStroke(ArrayList<Achievement> breastStroke) {
    this.breastStrokeResults = breastStroke;
  }

  public String toString() {
    return name + ", " + birth + ", " + getCompetitive() + " - " + getActive();
  }
}
