package net.suncheol.workalendar;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class WorkCalendar {
    private final Calendar calendar;
    private final Map<Integer, Set> holidays;

    public WorkCalendar() {
        this.calendar = Calendar.getInstance();
        this.holidays = new HashMap<>();
    }

    /**
     * Get calendar holidays.
     * This method **must** return a set or a list.
     * You must override this method for each calendar.\
     *
     * @param year
     * @return Set
     */
    public abstract Set getCalendarHolidays(int year);

    /**
     * Computes holidays (non-working days) for a given year
     *
     * @return
     */
    public Set holidays() {
        return holidays(calendar.get(Calendar.YEAR));
    }

    /**
     * Computes holidays (non-working days) for a given year
     *
     * @param year
     * @return
     */
    private Set holidays(int year) {
        if (this.holidays.containsKey(year)) {
            return holidays.get(year);
        }

        holidays.put(year, getCalendarHolidays(year));

        return this.holidays.get(year);
    }

    /**
     * Return a list (or a tuple) of weekdays that are *not* workdays.
     *
     * e.g: return (SAT, SUN,)
     */
    void get_weekend_days() {
        throw new UnsupportedOperationException("Your Calendar class must implement the `get_weekend_days` method");
    }


    /**
     * Return True if it's a workday.
     *
     * @param day
     * @return
     */
    public boolean isWorkday(LocalDate day) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add `delta` workdays to the date.
     *
     * @param day
     * @param delta
     * @return
     */
    public LocalDate addWorkdays(LocalDate day, int delta) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return the date of the easter (sunday) -- following the easter method
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterSunday(int year) {
        throw new UnsupportedOperationException();
    }


    /**
     * Return the date of the monday after easter
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterMonday(int year) {
        throw new UnsupportedOperationException();
    }


    /**
     * Get the nth weekday in a given month. e.g:
     *
     * >>> # the 1st monday in Jan 2013
     * >>> Calendar.get_nth_weekday_in_month(2013, 1, MON)
     * datetime.date(2013, 1, 7)
     * >>> # The 2nd monday in Jan 2013
     * >>> Calendar.get_nth_weekday_in_month(2013, 1, MON, 2)
     * datetime.date(2013, 1, 14)
     *
     * @param year
     * @param month
     * @param weekday
     * @return LocalDate
     */
    public static  LocalDate getNthWeekdayInMonth(int year, int month, int weekday) {
        return getNthWeekdayInMonth(year, month, weekday, 1);
    }

    private static LocalDate getNthWeekdayInMonth(int year, int month, int weekday, int n) {
        throw new UnsupportedOperationException();
    }
}
