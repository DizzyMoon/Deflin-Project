package event;

import java.util.ArrayList;

public class EventList {
    private ArrayList<Swimmeet> schedule = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public ArrayList<Swimmeet> getList() {
            return schedule;
        }

    public String toString() {
        for (Swimmeet event : schedule) {
        sb.append(event + "\n");
        }
        return String.valueOf(sb);
    }

    public Swimmeet findMeet(String eventName, EventList eventList) {
        for (int i = 0; i < eventList.getList().size(); i++) {
            if (eventList.getList().get(i).getEventName().equals(eventName))
                return eventList.getList().get(i);
            }
            return null;
    }
}

