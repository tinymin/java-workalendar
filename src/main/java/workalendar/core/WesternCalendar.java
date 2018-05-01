package workalendar.core;

import workalendar.core.model.Day;
import workalendar.core.model.FixedDay;
import workalendar.util.Easter.EASTER;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

// TODO WesternCalendar 컨버팅
/**
 * General usage calendar for Western countries.
 *
 * (chiefly Europe and Northern UnitedStates)
 */
public class WesternCalendar extends Workalendar {
    private EASTER EASTER_METHOD;
    private boolean shiftNewYearsDay;

    public WesternCalendar() {
        super();
        EASTER_METHOD = EASTER.WESTERN;
        WEEKEND_DAYS.addAll(Arrays.asList(SATURDAY, SUNDAY));
        FIXED_HOLIDAYS.add(new FixedDay(1, 1, "New Year"));
        this.shiftNewYearsDay = false;
    }


    @Override
    public SortedSet<Day> getVariableDays(int year) {
        SortedSet<Day> days = this.getVariableDays(year);
        LocalDate newYear = LocalDate.of(year, 1, 1);
        if (this.shiftNewYearsDay) {
            if ( this.getWeekendDays().contains(newYear.getDayOfWeek()) ) {
                days.add(new Day(this.findFollowingWorkingDay(newYear),"New Year shift"));
            }
        }
        return days;
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
