package workalendar.core;

import workalendar.core.model.Day;
import workalendar.core.model.FixedDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

abstract class Workalendar {
    private final Map<Integer, SortedSet<Day>> holidays;

    protected SortedSet<FixedDay> FIXED_HOLIDAYS;
    protected List<DayOfWeek> WEEKEND_DAYS;


    public Workalendar() {
        this.holidays = new HashMap<>();
        this.FIXED_HOLIDAYS =  new TreeSet<>();
        this.WEEKEND_DAYS =  new ArrayList<>();
    }

    /**
     * Return the fixed days according to the FIXED_HOLIDAYS class property
     */
    public SortedSet<Day> getFixedHolidays(int year) {
        SortedSet<Day> days =  new TreeSet<>();

        for (FixedDay fixedDay: this.FIXED_HOLIDAYS) {
            days.add(fixedDay.toDay(year));
        }

        return days;
    }

    public abstract SortedSet<Day> getVariableDays(int year);

    /**
     * Get calendar holidays.
     * If you want to override this, please make sure that it **must** return
     * a list of Days.
     *
     * @param year
     * @return Set<LocalDate>
     */
    public SortedSet<Day> getCalendarHolidays(int year) {
        SortedSet<Day> hd = this.getFixedHolidays(year);
        hd.addAll(this.getVariableDays(year));
        return hd;
    }

    /**
     * Computes holidays (non-working days) for a given year.
     * Return a 2-item tuple, composed of the date and a label.
     *
     * @return Set<LocalDate>
     */
    public SortedSet<Day> holidays() {
        return holidays(now().getYear());
    }

    /**
     * Computes holidays (non-working days) for a given year.
     * Return a 2-item tuple, composed of the date and a label.
     *
     * @param year
     * @return Set<LocalDate>
     */
    public SortedSet<Day> holidays(int year) {
        if (this.holidays.containsKey(year)) {
            return holidays.get(year);
        }

        // it is sorted
        holidays.put(year, this.getCalendarHolidays(year));

        return this.holidays.get(year);
    }


    /**
     * Return the label of the holiday, if the date is a holiday
     *
     * @param day
     * @return String Holiday label
     */
    public String getHolidayLabel(LocalDate day) {
        Map<LocalDate, String> h = this.holidays(day.getYear()).stream()
                .collect(toMap(k -> k.getLocalDate(), v -> v.getLabel()));

        return h.get(day);
    }


    /**
     * Return a quick date index (set)
     *
     * @return Set<LocalDate>
     */
    public Set<LocalDate> holidaysSet() {
        return holidaysSet(now().getYear());
    }

    public Set<LocalDate> holidaysSet(int year) {
        return this.holidays(year).stream()
                .map(Day::getLocalDate)
                .collect(toCollection(HashSet::new));
    }


    /**
     * Return a list (or a tuple) of weekdays that are *not* working days.
     * e.g: return (SAT, SUN,)
     *
     * @return List<DayOfWeek>
     */
    public List<DayOfWeek> getWeekendDays() {
        if (false == this.WEEKEND_DAYS.isEmpty())
            return this.WEEKEND_DAYS;

        throw new UnsupportedOperationException("Your Calendar class must provide" +
                                                "WEEKEND_DAYS or implement the" +
                                                "`get_weekend_days` method");
    }


    /**
     * Return True if it's a working day.
     * In addition to the regular holidays, you can add exceptions.
     *
     * By providing ``extra_working_days``, you'll state that these dates
     * **are** working days.
     *
     * By providing ``extra_holidays``, you'll state that these dates **are**
     * holidays, even if not in the regular calendar holidays (or weekends).
     *
     * Please note that the ``extra_working_days`` list has priority over the
     * ``extra_holidays`` list.
     *
     * @param day
     * @return boolean
     */
    public boolean isWorkingDay(LocalDate day) {
        return isWorkingDay(day, null, null);
    }

    public boolean isWorkingDay(LocalDate day, List extraWorkingDays, List extraHolidays) {
        // Extra lists exceptions
        if (null != extraWorkingDays && extraWorkingDays.contains(day))
            return true;

        // Regular rules
        if (this.getWeekendDays().contains(day.getDayOfWeek()))
            return false;

        return !(isHoliday(day, extraHolidays));
    }


    /**
     * Return True if it's an holiday.
     * In addition to the regular holidays, you can add exceptions.
     *
     * By providing ``extra_holidays``, you'll state that these dates **are**
     * holidays, even if not in the regular calendar holidays (or weekends).
     *
     * @param day
     * @param extraHolidays
     * @return boolean
     */
    private boolean isHoliday(LocalDate day, List extraHolidays) {
        if (null != extraHolidays && extraHolidays.contains(day))
            return true;

        return this.holidaysSet(day.getYear()).contains(day);
    }


    /**
     * Add `delta` working days to the date.
     *
     * the ``delta`` parameter might be positive or negative. If it's
     * negative, you may want to use the ``sub_working_days()`` method with
     * a positive ``delta`` argument.
     *
     * By providing ``extra_working_days``, you'll state that these dates
     * **are** working days.
     *
     * By providing ``extra_holidays``, you'll state that these dates **are**
     * holidays, even if not in the regular calendar holidays (or weekends).
     *
     * Please note that the ``extra_working_days`` list has priority over the
     * ``extra_holidays`` list.
     *
     * @param day
     * @param delta
     */
    public LocalDate addWorkingDays(LocalDate day, int delta) {
        return addWorkingDays(day, delta, null, null);
    }

    public LocalDate addWorkingDays(LocalDate day, int delta, List extraWorkingDays, List extraHolidays) {
        int days = 0;
        LocalDate tempDay = day;
        int dayAdded = delta >= 0 ? 1 : -1;
        while (days < delta) {
            tempDay = tempDay.plusDays(dayAdded);
            if (this.isWorkingDay(tempDay, extraWorkingDays, extraHolidays)) {
                days += 1;
            }
        }

        return tempDay;
    }


    /**
     * Substract `delta` working days to the date.
     *
     * This method is a shortcut / helper. Users may want to use either::
     *
     * cal.add_working_days(my_date, -7)
     * cal.sub_working_days(my_date, 7)
     *
     * The other parameters are to be used exactly as in the
     * ``add_working_days`` method.
     *
     * A negative ``delta`` argument will be converted into its absolute
     * value. Hence, the two following calls are equivalent::
     *
     * cal.sub_working_days(my_date, -7)
     * cal.sub_working_days(my_date, 7)
     *
     * @param day
     * @param delta
     * @return LocalDate
     */
    public LocalDate subWorkingDays(LocalDate day, int delta) {
        return subWorkingDays(day, delta, null, null);
    }

    public LocalDate subWorkingDays(LocalDate day, int delta, List extraWorkingDays, List extraHolidays) {
        delta = Math.abs(delta);
        return addWorkingDays(day, delta*-1, extraWorkingDays, extraHolidays);
    }

    /**
     * Looks for the following working day
     *
     * @param day
     * @return LocalDate
     */
    public LocalDate findFollowingWorkingDay(LocalDate day) {
        while(this.getWeekendDays().contains(day.getDayOfWeek())) {
            day = day.plusDays(1);
        }

        return day;
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
        return getNthWeekdayInMonth(year, month, dayOfWeek, 1, null);
    }

    public static LocalDate getNthWeekdayInMonth(int year, int month, DayOfWeek dayOfWeek, int n) {
        return getNthWeekdayInMonth(year, month, dayOfWeek, n, null);
    }

    public static LocalDate getNthWeekdayInMonth(int year, int month, DayOfWeek dayOfWeek, int n, LocalDate start) {
        LocalDate day = null;

        if(day != null)
            day = start;
        else
            day = LocalDate.of(year, month, 1);

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
        // Get last day of month
        LocalDate day = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());

        while (true) {
            if (day.getDayOfWeek() == dayOfWeek)
                break;

            day = day.minusDays(1);
        }

        return day;
    }


    /**
     * Get the first weekday after a given day. If the day is the same
     *  weekday, the same day will be returned.
     *
     *  >>> # the first monday after Apr 1 2015
     *  >>> Calendar.get_first_weekday_after(date(2015, 4, 1), 0)
     *  datetime.date(2015, 4, 6)
     *
     *  >>> # the first tuesday after Apr 14 2015
     *  >>> Calendar.get_first_weekday_after(date(2015, 4, 14), 1)
     *  datetime.date(2015, 4, 14)
     *
     * @param day
     * @param dayOfWeek
     * @return LocalDate
     */
    public static LocalDate get_first_weekday_after(LocalDate day, DayOfWeek dayOfWeek) {
        int dayDelta = (dayOfWeek.ordinal() - day.getDayOfWeek().ordinal()) % 7;
        return day.plusDays(dayDelta);
    }
}
