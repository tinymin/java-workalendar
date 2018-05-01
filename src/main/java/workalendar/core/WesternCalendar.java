package workalendar.core;

import workalendar.core.model.Day;
import workalendar.core.model.FixedDay;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

// TODO WesternCalendar 컨버팅
/**
 * General usage calendar for Western countries.
 * <p>
 * (chiefly Europe and Northern UnitedStates)
 */
public class WesternCalendar extends Workalendar {

    public WesternCalendar() {
        super();
        WEEKEND_DAYS.addAll(Arrays.asList(SATURDAY, SUNDAY));
        FIXED_HOLIDAYS.add(new FixedDay(1, 1, "New Year"));
    }


    @Override
    public SortedSet<Day> getVariableDays(int year) {
        return null;
    }

    /**
     * European countries have at least these 2 days as holidays in common
     */
    @Override
    public SortedSet<Day> getCalendarHolidays(int year) {
        SortedSet<Day> days = new TreeSet<>();
        days.add(new Day(LocalDate.of(year, 1, 1), "New Year"));
        days.add(new Day(LocalDate.of(year, 12, 25), "Christmas"));
        return days;
    }
}
