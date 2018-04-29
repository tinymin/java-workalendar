package workalendar.region.usa;

import workalendar.core.Day;
import workalendar.core.FixedDay;
import workalendar.core.WesternCalendar;

import java.time.LocalDate;
import java.util.SortedSet;

import static java.time.DayOfWeek.*;


// TODO UnitedStates 재작업 필요
/**
 * United States of America
 */
public class UnitedStates extends WesternCalendar {

    public UnitedStates() {
        super();
        this.FIXED_HOLIDAYS.add(new FixedDay(7, 4, "Independence Day"));
    }

    public static Boolean isPresidentialYear(int year) {
        return (year % 4) == 0;
    }

    @Override
    public SortedSet<Day> getCalendarHolidays(int year) {
        SortedSet<Day> days = super.getCalendarHolidays(year);

        days.add(new Day(LocalDate.of(year, 7, 4), ""));
        days.add(new Day(LocalDate.of(year, 11, 11), ""));

        // Variable days
        days.add(new Day(WesternCalendar.getNthWeekdayInMonth(year, 1, MONDAY, 3), ""));
        days.add(new Day(WesternCalendar.getNthWeekdayInMonth(year, 2, MONDAY, 3), ""));
        days.add(new Day(WesternCalendar.getLastWeekdayInMonth(year, 5, MONDAY), ""));
        days.add(new Day(WesternCalendar.getNthWeekdayInMonth(year, 9, MONDAY), ""));
        days.add(new Day(WesternCalendar.getNthWeekdayInMonth(year, 10, MONDAY, 2), ""));
        days.add(new Day(WesternCalendar.getNthWeekdayInMonth(year, 11, THURSDAY, 4), ""));

        // Inauguration day
        if (UnitedStates.isPresidentialYear(year - 1)) {
            LocalDate inaugurationDay = LocalDate.of(year, 1, 20);

            if (inaugurationDay.getDayOfWeek() == SUNDAY) {
                inaugurationDay = LocalDate.of(year, 1, 21);
            }

            days.add(new Day(inaugurationDay, ""));
        }

        return days;
    }
}
