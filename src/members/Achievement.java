package members;

import java.util.Date;

public class Achievement {
    private String dicipline;
    private Date date;                  // idea: use the time field, to store the finish time, sparing a variable
    private int distance;
    private String medal;

    public Achievement(String dicipline, Date date, int distance){
        this.dicipline = dicipline;
        this.date = date;
        this.distance = distance;
    }

    public String getDicipline(){
        return dicipline;
    }

    public Date getDate(){
        return date;
    }

    public int getDistance() { return distance; }

    public void setMedal(String medal) { this.medal = medal; }

// maybe other setters too, need for... later edit/addition to meet?
    public void setDicipline(String dicipline){
        this.dicipline = dicipline;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setDistance(int distance) { this.distance = distance; }
}
