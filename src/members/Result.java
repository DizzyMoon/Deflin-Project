package members;

import java.util.Date;

public class Result {
    private double resultTime;
    private Date date;

    public Result(double resultTime, Date date){
        this.resultTime = resultTime;
        this.date = date;
    }

    public double getResultTime(){
        return resultTime;
    }

    public Date getDate(){
        return date;
    }

    public void setResultTime(double resultTime){
        this.resultTime = resultTime;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
