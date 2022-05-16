package members;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class Member {
    private String name;
    private UUID memberID;
    private final Date birth;
    private boolean competitive;
    private double arrears;
    private boolean active;
    private ArrayList<Achievement> butterflyResults;
    private ArrayList<Achievement> crawlResults;
    private ArrayList<Achievement> backstrokeResults;
    private ArrayList<Achievement> breastStroke;

    public Member(String name, Date birth, boolean competitive, boolean active) {
        this.name = name;
        this.active = active;
        this.birth = birth;
        this.competitive = competitive;
    }


    public String getActive(){
        return this.active ? "Member is active" : "Member is not active";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberID(UUID memberID) {
        this.memberID = memberID;
    }

    public Date getBirth() {
        return birth;
    }

    public void setCompetitive(boolean competitive) {
        this.competitive = competitive;
    }

    public void setArrears(double arrears) {
        this.arrears = arrears;
    }

    public String getName() {
        return name;
    }

    public UUID getMemberID() {
        return memberID;
    }

    public String getCompetitive() {
        return competitive ? "Competitive member" : "Exercise member";
    }

    public double getArrears() {
        return arrears;
    }

    public void setActive(boolean active){
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

    public ArrayList<Achievement> getBackstrokeResults() {
        return backstrokeResults;
    }

    public void setBackstrokeResults(ArrayList<Achievement> backstrokeResults) {
        this.backstrokeResults = backstrokeResults;
    }

    public ArrayList<Achievement> getBreastStroke() {
        return breastStroke;
    }

    public void setBreastStroke(ArrayList<Achievement> breastStroke) {
        this.breastStroke = breastStroke;
    }

    public String toString (){
        return name + ", " + birth + ", " + getCompetitive() + " - " + getActive();
    }
}
