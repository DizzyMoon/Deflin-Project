package members;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Swimmeet {

    private String eventName;
    private char genderCategory;
    private boolean juniorMeet;
    private LocalDate eventDate;
    private ArrayList<Member> backstrokeComp = new ArrayList<>();
    private ArrayList<Member> breaststrokeComp = new ArrayList<>();
    private ArrayList<Member> butterflyComp = new ArrayList<>();
    private ArrayList<Member> crawlComp = new ArrayList<>();

    public Swimmeet(LocalDate eventDate) {
        this.eventName = eventName;
        this.genderCategory = genderCategory;
        this.juniorMeet = juniorMeet;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public boolean isJuniorMeet() {
        return juniorMeet;
    }

    /*  public ArrayList getBackstrokeComp() { return backstrokeComp; }
        public ArrayList getBreaststrokeComp() { return breaststrokeComp; }
        public ArrayList getButterflyComp() { return butterflyComp; }
        public ArrayList getCrawlComp() { return crawlComp; }
    */
    public void addSwimmer(Member contender, ArrayList comp) {
        comp.add(contender);
    }

    public ArrayList viewCompAssignment(int discipline) {
        ArrayList<Member> competitorList = new ArrayList<>();
        switch (discipline) {
            case 1 -> {
                competitorList = backstrokeComp;
            }
            case 2 -> {
                competitorList = breaststrokeComp;
            }
            case 3 -> {
                competitorList = butterflyComp;
            }
            case 4 -> {
                competitorList = crawlComp;
            }
        }
        return competitorList;
    }
}

