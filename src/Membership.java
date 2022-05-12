import java.util.ArrayList;
import java.util.Date;

abstract class Membership {
    private String name;
    private String memberID;
    private final Date birth;
    private final Date regDate;
    private boolean competitive;
    private boolean activeStatus;
    private double arrears;
    private ArrayList<Achievement> backstrokeResults;
    private ArrayList<Achievement> breaststrokeResults;
    private ArrayList<Achievement> butterflyResults;
    private ArrayList<Achievement> crawlResults;
    //private ArrayList                 maybe put something here, maybe not

    public Membership(String name, Date birth, Date regDate, boolean competitive) {
        this.name = name;
        this.birth = birth;
        this.regDate = regDate;
        this.competitive = competitive;
        }

    public void setName(String name) {
        this.name = name;
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

    public void setButterflyResults(ArrayList<Achievement> butterflyResults) {this.butterflyResults = butterflyResults;}

    public ArrayList<Achievement> getCrawlResults() {
        return crawlResults;
    }

    public void setCrawlResults(ArrayList<Achievement> crawlResults) {
        this.crawlResults = crawlResults;
    }

    public ArrayList<Achievement> getBackstrokeResults() {
        return backstrokeResults;
    }

    public void setBackstrokeResults(ArrayList<Achievement> backcrawlResults) { this.backstrokeResults = backcrawlResults;}

    public ArrayList<Achievement> getBreaststrokeResults() {
        return breaststrokeResults;
    }

    public void setBreastStroke(ArrayList<Achievement> breastStroke) {
        this.breaststrokeResults = breaststrokeResults;
    }
}
