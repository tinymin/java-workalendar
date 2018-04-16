package net.suncheol.workalendar;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * General usage calendar for Western countries.
 *
 * (chiefly Europe and Northern America)
 */
class WesternCalendar {
    private final Set<Day> WEEK_END_DAYS;

    WesternCalendar() {
        WEEK_END_DAYS = new HashSet<>(Arrays.asList(Day.SAT, Day.SUN));
    }

    /**
     * European countries have at least these 2 days as holidays in common
     */
    public Set<LocalDate> getCalendarHolidays(int year) {
        Set<LocalDate> days = new HashSet<>();
        days.add(LocalDate.of(year, 1, 1));
        days.add(LocalDate.of(year, 12, 25));
        return days;
    }

    /**
     * Week-end days are SATurday and SUNday.
     */
    public Set<Day> getWeekendDays() {
        return this.WEEK_END_DAYS;
    }
}
