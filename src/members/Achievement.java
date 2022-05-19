package members;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class Achievement {
    private String discipline;
    private LocalDateTime result;                  // use time field, to store the finish time
    private int distance;
    private String medal;

    public Achievement(String dicipline, LocalDateTime result, int distance){
        this.discipline = dicipline;
        this.result = result;
        this.distance = distance;
    }

    public String getDiscipline(){
        return discipline;
    }

    public LocalDateTime getTime(){
        return result;
    }

    public int getDistance() { return distance; }

    public void setMedal(String medal) { this.medal = medal; }

// maybe other setters too, need for... later edit/addition to meet?
    public void setDiscipline(String discipline){
        this.discipline = discipline;
    }

    public void setTime(LocalDateTime result){
        this.result = result;
    }

    public void setDistance(int distance) { this.distance = distance; }

    public int compareTo(Achievement o) {
        return this.getTime().compareTo(o.getTime());
    }
/*
    public boolean sortFastest(Achievement otherAchievement) {
        return fasterThan((Achievement) otherAchievement);

    }

    public boolean fasterThan(Achievement otherAchievement) {
        Period period = Period.between()
        if (otherAchievement.result < this.result) {
            return true;
        } else {
            return false;
        }
    }*/
}
