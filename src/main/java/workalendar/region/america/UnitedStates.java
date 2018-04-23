package workalendar.region.america;

import workalendar.core.WesternCalendar;

import java.time.LocalDate;
import java.util.Set;

import static java.time.DayOfWeek.*;


/**
 * USA Calendar
 */
public class UnitedStates extends WesternCalendar {
    public UnitedStates() {
        super();
    }

    public static Boolean isPresidentialYear(int year) {
        return (year % 4) == 0;
    }

    @Override
    public Set<LocalDate> getCalendarHolidays(int year) {
        Set<LocalDate> days = super.getCalendarHolidays(year);
        days.add(LocalDate.of(year, 7, 4));
        days.add(LocalDate.of(year, 11, 11));

        // Variable days
        days.add(WesternCalendar.getNthWeekdayInMonth(year, 1, MONDAY, 3));
        days.add(WesternCalendar.getNthWeekdayInMonth(year, 2, MONDAY, 3));
        days.add(WesternCalendar.getLastWeekdayInMonth(year, 5, MONDAY));
        days.add(WesternCalendar.getNthWeekdayInMonth(year, 9, MONDAY));
        days.add(WesternCalendar.getNthWeekdayInMonth(year, 10, MONDAY, 2));
        days.add(WesternCalendar.getNthWeekdayInMonth(year, 11, THURSDAY, 4));

        // Inauguration day
        if (UnitedStates.isPresidentialYear(year - 1)) {
            LocalDate inaugurationDay = LocalDate.of(year, 1, 20);

            if (inaugurationDay.getDayOfWeek() == SUNDAY) {
                inaugurationDay = LocalDate.of(year, 1, 21);
            }

            days.add(inaugurationDay);
        }

        return days;
    }
}
