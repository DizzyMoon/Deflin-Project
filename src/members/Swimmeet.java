package members;

import java.time.LocalDate;
import java.util.ArrayList;

public class Swimmeet {

    private String eventName;
    private LocalDate eventDate;
    private boolean juniorMeet;
    private ArrayList<Member> backstrokeComp = new ArrayList<>();
    private ArrayList<Member> breaststrokeComp = new ArrayList<>();
    private ArrayList<Member> butterflyComp = new ArrayList<>();
    private ArrayList<Member> crawlComp = new ArrayList<>();

    public Swimmeet (LocalDate eventDate) { this.eventName = eventName; this.eventDate = eventDate; }

    public String getEventName() { return eventName; }
    public LocalDate getEventDate() { return eventDate; }
    public boolean isJuniorMeet() { return juniorMeet; }
    public ArrayList getBackstrokeComp() { return backstrokeComp; }
    public ArrayList getBreaststrokeComp() { return breaststrokeComp; }
    public ArrayList getButterflyComp() { return butterflyComp; }
    public ArrayList getCrawlComp() { return crawlComp; }

    //public addSwimmer (Member contender, ArrayList comp) {}

    }
