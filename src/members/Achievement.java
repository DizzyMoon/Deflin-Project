package members;

import java.util.Date;

public class Achievement {
    private String dicipline;
    private Date date;

    public Achievement(String dicipline, Date date){
        this.dicipline = dicipline;
        this.date = date;
    }

    public String dicipline(){
        return dicipline;
    }

    public Date getDate(){
        return date;
    }

    public void setDicipline(String dicipline){
        this.dicipline = dicipline;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
