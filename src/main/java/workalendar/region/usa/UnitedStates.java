package workalendar.region.usa;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import workalendar.core.WesternCalendar;
import workalendar.core.model.Day;
import workalendar.core.model.FixedDay;

import java.util.SortedSet;


// TODO UnitedStates 재작업 필요
/**
 * United States of America
 */
public class UnitedStates implements WesternCalendar {

    public UnitedStates() {
        super();
        this.FIXED_HOLIDAYS.add(new FixedDay(7, 4, "Independence Day"));
    }

    public static Boolean isPresidentialYear(int year) {
        return (year % 4) == 0;
    }



    /**
     * Will return holidays and their shifted days
     *
     * @param year
     * @return
     */
    @Override
    public SortedSet<Day> getCalendarHolidays(int year) {
        SortedSet<Day> days = WesternCalendar.super.getCalendarHolidays(year);
        days = this.shift(days, year);

        return days;
    }

    private SortedSet<Day> shift(SortedSet<Day> days, int year) {
        throw new NotImplementedException();
    }
}
