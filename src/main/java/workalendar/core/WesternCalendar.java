package workalendar.core;

import workalendar.core.model.Day;
import workalendar.util.Easter.EASTER;

import java.time.LocalDate;
import java.util.SortedSet;

// TODO WesternCalendar 컨버팅
/**
 * General usage calendar for Western countries.
 *
 * (chiefly Europe and Northern UnitedStates)
 */
public interface WesternCalendar extends Workalendar {
    EASTER EASTER_METHOD = EASTER.WESTERN;

    @Override
    default SortedSet<Day> getVariableDays(int year) {
        SortedSet<Day> days = Workalendar.super.getVariableDays(year);
        LocalDate newYear = LocalDate.of(year, 1, 1);
        if (this.isShiftNewYearsDay()) {
            if ( this.getWeekendDays().contains(newYear.getDayOfWeek()) ) {
                days.add(new Day(this.findFollowingWorkingDay(newYear),"New Year shift"));
            }
        }
        return days;
    }


    @Override
    default boolean isShiftNewYearsDay() {
        return false;
    }
}
