package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

/**
 *
 */
public class ScheduleList {

    private ArrayList<Schedule> scheduleList = new ArrayList<>();

    /**
     * Replaces the contents of this list with {@code exercises}. {@code exercises} must
     * not contain duplicate exercises.
     */
    public void setSchedule(ArrayList<Schedule> newScheduleList) {
        requireAllNonNull(newScheduleList);
        scheduleList = newScheduleList;
    }

    public ArrayList<Schedule> getScheduleList() {
        return scheduleList;
    }

    @Override
    public int hashCode() {
        return scheduleList.hashCode();
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (Schedule schedule : scheduleList) {
            toReturn += schedule.toString() + "\n";
        }
        return toReturn.trim();
    }
}
