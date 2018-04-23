package workalendar.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

/**
 * General usage calendar for Western countries.
 * <p>
 * (chiefly Europe and Northern UnitedStates)
 */
public class WesternCalendar extends Workalendar {
    private final Set<DayOfWeek> WEEK_END_WEEKDAYS;

    public WesternCalendar() {
        super();
        WEEK_END_WEEKDAYS = new HashSet<>(Arrays.asList(SATURDAY, SUNDAY));
    }

    /**
     * European countries have at least these 2 days as holidays in common
     */
    @Override
    public Set<LocalDate> getCalendarHolidays(int year) {
        Set<LocalDate> days = new HashSet<>();
        days.add(LocalDate.of(year, 1, 1));
        days.add(LocalDate.of(year, 12, 25));
        return days;
    }

    /**
     * Week-end days are SATurday and SUNday.
     */
    @Override
    public Set getWeekendDays() {
        return this.WEEK_END_WEEKDAYS;
    }
}
