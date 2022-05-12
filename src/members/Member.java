package members;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

abstract class Member {
    private String name;
    private UUID memberID;
    private final Date birth;
    private boolean competitive;
    private double restance;
    private boolean active;
    private ArrayList<Result> butterflyResults;
    private ArrayList<Result> crawlResults;
    private ArrayList<Result> rygcrawlResults;
    private ArrayList<Result> breastStroke;

    public Member(String name, Date birth, boolean competitive, boolean active) {
        this.name = name;
        this.active = active;
        this.birth = birth;
        this.competitive = competitive;
    }


    public String getActive(){
        return this.active ? "members.Member is active" : "members.Member is not active";
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

    public void setRestance(double restance) {
        this.restance = restance;
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

    public double getRestance() {
        return restance;
    }

    public void setActive(boolean active){
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

    public ArrayList<Result> getBreastStroke() {
        return breastStroke;
    }

    public void setBreastStroke(ArrayList<Result> breastStroke) {
        this.breastStroke = breastStroke;
    }
}
