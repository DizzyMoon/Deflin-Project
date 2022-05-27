package Achievement;

import java.util.ArrayList;

public class AchievementList {
  private ArrayList<Achievement> achievements = new ArrayList<Achievement>();

  public ArrayList<Achievement> getAchievements(){
    return achievements;
  }

  public String toString(){
    return String.valueOf(achievements);
  }

  public void setList(ArrayList<Achievement> achievements){
    this.achievements = achievements;
  }
}
