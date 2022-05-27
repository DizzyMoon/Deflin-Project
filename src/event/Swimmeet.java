package event;

import members.Member;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Swimmeet {

    private String eventName;
    private String genderCategory;
    private boolean juniorLeague;
    private LocalDateTime eventTime;
    private ArrayList<Member> backstrokeComp = new ArrayList<>();
    private ArrayList<Member> breaststrokeComp = new ArrayList<>();
    private ArrayList<Member> butterflyComp = new ArrayList<>();
    private ArrayList<Member> crawlComp = new ArrayList<>();

    public Swimmeet(String eventName, String genderCategory, boolean juniorLeague, LocalDateTime eventTime) {
        this.eventName = eventName;
        this.genderCategory = genderCategory;
        this.juniorLeague = juniorLeague;
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getGenderCategory() {
        return genderCategory;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public String isJuniorLeague() {
        return juniorLeague ? "Junior" : "Senior";
    }

    /*  public ArrayList getBackstrokeComp() { return backstrokeComp; }
        public ArrayList getBreaststrokeComp() { return breaststrokeComp; }
        public ArrayList getButterflyComp() { return butterflyComp; }
        public ArrayList getCrawlComp() { return crawlComp; }
    */
    public ArrayList chosenDiscipline(int choice) {
        ArrayList<Member> returned;
        switch (choice) {
            case 1 -> returned = backstrokeComp;
            case 2 -> returned = breaststrokeComp;
            case 3 -> returned = butterflyComp;
            default -> returned = crawlComp;
        }
        return returned;
    }

    public void assignCompetitor(Member contender, ArrayList comp) {
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

