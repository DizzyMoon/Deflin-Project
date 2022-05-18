package members;

import java.util.ArrayList;
import java.util.Date;

public class Swimmeet {

    private String eventName;
    private Date eventDate;
    private boolean juniorMeet;
    private ArrayList<Member> backstrokeComp = new ArrayList<>();
    private ArrayList<Member> breaststrokeComp = new ArrayList<>();
    private ArrayList<Member> butterflyComp = new ArrayList<>();
    private ArrayList<Member> crawlComp = new ArrayList<>();

    public Swimmeet (Date eventDate) { this.eventName = eventName; this.eventDate = eventDate; }

    public String getEventName() { return eventName; }
    public Date getEventDate() { return eventDate; }
    public boolean isJuniorMeet() { return juniorMeet; }
    public ArrayList getBackstrokeComp() { return backstrokeComp; }
    public ArrayList getBreaststrokeComp() { return breaststrokeComp; }
    public ArrayList getButterflyComp() { return butterflyComp; }
    public ArrayList getCrawlComp() { return crawlComp; }

    //public addSwimmer (Member contender, ArrayList comp) {}

    }
