package members;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import Achievement.*;

public class Achievement {
    private String memberID;
    private Discipline discipline;
    private LocalDateTime result;                  // use time field, to store the finish time
    private int distance;
    private Medal medal;
    private String comment;

    public Achievement(Discipline discipline, LocalDateTime result, int distance){
        this.discipline = discipline;
        this.result = result;
        this.distance = distance;
    }

    public Achievement(String memberID, Discipline discipline, LocalDateTime result, int distance, Medal medal, String comment){
        this.memberID = memberID;
        this.discipline = discipline;
        this.result = result;
        this.distance = distance;
        this.medal = medal;
        this.comment = comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public LocalDateTime getResult(){
        return result;
    }

    public String getComment(){
        return comment;
    }

    public Medal getMedal(){
        return medal;
    }

    public Discipline getDiscipline(){
        return discipline;
    }

    public LocalDateTime getTime(){
        return result;
    }

    public int getDistance() { return distance; }

    public void setMedal(Medal medal) { this.medal = medal; }

// maybe other setters too, need for... later edit/addition to meet?
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
