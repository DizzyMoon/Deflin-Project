import java.util.Date;

public class Achievement {
    private double resultTime;
    private Date date;
    private String medal;

    public Achievement(double resultTime, Date date){
        this.resultTime = resultTime;
        this.date = date;
        this.medal = medal;
    }

    public double getResultTime(){
        return resultTime;
    }

    public Date getDate(){
        return date;
    }

    public String getMedal() {return medal; }

    public void setResultTime(double resultTime){
        this.resultTime = resultTime;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
