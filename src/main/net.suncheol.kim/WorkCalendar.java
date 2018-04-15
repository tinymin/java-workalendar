package net.suncheol.kim;


import java.util.Calendar;
import java.util.Set;

public abstract class WorkCalendar {
    private Calendar calendar;

    public WorkCalendar() {
        this.calendar = Calendar.getInstance();
    }

    public abstract Set getCalendarHolidays();

    public Set holidays() {
        return holidays(calendar.get(Calendar.YEAR));
    }

    public abstract Set holidays(int year);
}
