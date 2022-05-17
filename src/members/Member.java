package members;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;

public abstract class Member {

    private String name;
    private String memberID;
    private final Date birth;
    private String phoneNumber;
    private String email;
    private boolean competitive;
    private double arrears;
    private boolean active;
    private ArrayList<Achievement> butterflyResults;
    private ArrayList<Achievement> crawlResults;
    private ArrayList<Achievement> backstrokeResults;
    private ArrayList<Achievement> breaststrokeResults;


    public Member(String name, Date birth, String phoneNumber, String email, boolean competitive, boolean active) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.active = active;
        this.birth = birth;
        this.competitive = competitive;
    }


    public String getActive(){
        return this.active ? "Aktivt" : "Inaktivt";
    }   // Skal i UI-klasse?

    public boolean getActiveBool(){
        return this.active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setEmail(String email){
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
        return competitive ? "Competitive member" : "Exercise member";
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

    public void setBackstrokeResults(ArrayList<Achievement> backstrokeResults) { this.backstrokeResults = backstrokeResults; }

    public ArrayList<Achievement> getBreaststrokeResults() {
        return breaststrokeResults;
    }

    public void setBreastStroke(ArrayList<Achievement> breaststrokeResults) {
        this.breaststrokeResults = breaststrokeResults;
    }

    public String toString (){
        return name + ", " + birth + ", " + getCompetitive() + " - " + getActive();
    }
}
