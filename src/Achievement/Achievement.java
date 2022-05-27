package Achievement;

import java.time.LocalDateTime;

public class Achievement {
    private String memberID;
    private Discipline discipline;
    private LocalDateTime result;                  // use time field, to store the finish time
    private int distance;
    private String medal;
    private int placement;
    private String event;

    public Achievement(Discipline discipline, LocalDateTime result, int distance){
        this.discipline = discipline;
        this.result = result;
        this.distance = distance;
    }

    public Achievement(String memberID, Discipline discipline, LocalDateTime result, int distance, int placement, String medal, String event){
        this.memberID = memberID;
        this.discipline = discipline;
        this.result = result;
        this.distance = distance;
        this.placement = placement;
        this.medal = medal;
        this.event = event;
    }

    public int getPlacement(){
        return placement;
    }

    public void setPlacement(int placement){
        this.placement = placement;
    }

    public String getEvent(){
        return event;
    }

    public void setEvent(String event){
        this.event = event;
    }

    public LocalDateTime getResult(){
        return result;
    }

    public String getMedal(){
        return medal;
    }

    public Discipline getDiscipline(){
        return discipline;
    }

    public LocalDateTime getTime(){
        return result;
    }

    public int getDistance() { return distance; }

    public void setMedal(String medal) { this.medal = medal; }


    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }

    public void setTime(LocalDateTime result){
        this.result = result;
    }

    public void setDistance(int distance) { this.distance = distance; }

    public int compareTo(Achievement o) {
        return this.getTime().compareTo(o.getTime());
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberID() {
        return memberID;
    }

    @Override
    public String toString() {
        return "Achievement{" +
            "memberID='" + memberID + '\'' +
            ", discipline='" + discipline + '\'' +
            ", result=" + result +
            ", distance=" + distance +
            ", medal='" + medal + '\'' +
            '}';
    }
}
