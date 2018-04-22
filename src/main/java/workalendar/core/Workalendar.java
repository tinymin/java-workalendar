package workalendar.core;

import workalendar.util.Easter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class Workalendar {
    private final Calendar calendar;
    private final Map<Integer, Set<LocalDate>> holidays;

    public Workalendar() {
        this.calendar = Calendar.getInstance();
        this.holidays = new HashMap<>();
    }

    /**
     * Get calendar holidays.
     * This method **must** return a set or a list.
     * You must override this method for each calendar.\
     *
     * @param year
     * @return Set<LocalDate>
     */
    public abstract Set<LocalDate> getCalendarHolidays(int year);

    /**
     * Computes holidays (non-working days) for a given year
     *
     * @return Set<LocalDate>
     */
    public Set<LocalDate> holidays() {
        return holidays(calendar.get(Calendar.YEAR));
    }

    /**
     * Computes holidays (non-working days) for a given year
     *
     * @param year
     * @return Set<LocalDate>
     */
    public Set<LocalDate> holidays(int year) {
        if (this.holidays.containsKey(year)) {
            return holidays.get(year);
        }

        holidays.put(year, getCalendarHolidays(year));

        return this.holidays.get(year);
    }


    /**
     * Return a list of weekdays that are *not* workdays.
     * <p>
     * e.g: return (SAT, SUN,)
     *
     * @return Set<LocalDate>
     */
    public Set getWeekendDays() {
        throw new UnsupportedOperationException("Your Calendar class must implement the `get_weekend_days` method");
    }


    /**
     * Return True if it's a workday.
     *
     * @param day
     * @return boolean
     */
    public boolean isWorkday(LocalDate day) {
        if (this.getWeekendDays().contains(day))
            return false;

        if (this.holidays(day.getYear()).contains(day))
            return false;

        return true;
    }

    /**
     * Add `delta` workdays to the date.
     *
     * @param day
     * @param delta
     * @return LocalDate
     */
    public LocalDate addWorkdays(LocalDate day, int delta) {
        int days = 0;
        LocalDate tempDay = day;

        while (days < delta) {
            tempDay = tempDay.plusDays(1);
            if (this.isWorkday(tempDay)) {
                days += 1;
            }
        }

        return tempDay;
    }

    /**
     * Return the date of the easter (sunday) -- following the easter method
     * Easter Sunday = 부활절 일요일
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterSunday(int year) {
        return Easter.sundayFor(Year.of(year));
    }


    /**
     * Return the date of the monday after easter
     * Easter Monday = 부활절 다음 날 월요일
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterMonday(int year) {
        LocalDate easterSunday = getEasterSunday(year);
        return easterSunday.plusDays(1);
    }


    /**
     * Get the nth weekday in a given month. e.g:
     * <p>
     * >>> # the 1st monday in Jan 2013
     * >>> Calendar.get_nth_weekday_in_month(2013, 1, MON)
     * datetime.date(2013, 1, 7)
     * >>> # The 2nd monday in Jan 2013
     * >>> Calendar.get_nth_weekday_in_month(2013, 1, MON, 2)
     * datetime.date(2013, 1, 14)
     *
     * @param year
     * @param month
     * @param dayOfWeek
     * @return LocalDate
     */
    public static LocalDate getNthWeekdayInMonth(int year, int month, DayOfWeek dayOfWeek) {
        return getNthWeekdayInMonth(year, month, dayOfWeek, 1);
    }

    public static LocalDate getNthWeekdayInMonth(int year, int month, DayOfWeek dayOfWeek, int n) {
        LocalDate day = LocalDate.of(year, month, 1);
        int counter = 0;
        while (true) {
            if (day.getMonth().getValue() != month)
                return null;

            if (day.getDayOfWeek() == dayOfWeek)
                counter += 1;

            if (counter == n)
                break;

            day = day.plusDays(1);
        }

        return day;
    }

    /**
     * Get the last weekday in a given month. e.g:
     * <p>
     * >>> # the last monday in Jan 2013
     * >>> Calendar.get_last_weekday_in_month(2013, 1, MON)
     * datetime.date(2013, 1, 28)
     *
     * @param year
     * @param month
     */
    public static LocalDate getLastWeekdayInMonth(int year, int month, DayOfWeek dayOfWeek) {
        LocalDate day = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());

        while (true) {
            if (day.getDayOfWeek() == dayOfWeek)
                break;

            day = day.minusDays(1);
        }

        return day;
    }
}
