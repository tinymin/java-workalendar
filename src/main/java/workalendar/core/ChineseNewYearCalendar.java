package workalendar.core;

import lombok.Data;
import workalendar.core.model.Day;
import workalendar.core.model.DayComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.time.DayOfWeek.SUNDAY;

/**
 * Calendar including toolsets to compute the Chinese New Year holidays.
 */
@Data
public class ChineseNewYearCalendar extends WesternCalendar {
    private boolean includeChineseNewYearEve;
    private String chineseNewYearEveLabel;
    // Chinese New Year will be included by default
    private boolean includeChineseNewYear;
    private String chineseNewYearLabel;
    // Some countries include the 2nd lunar day as a holiday
    private boolean includeChineseSecondDay;
    private String chineseSecondDayLabel;
    private boolean includeChineseThirdDay;
    private String chineseThirdDayLabel;
    private boolean shiftSundayHolidays;
    // Some calendars roll a starting Sunday CNY to Sat
    private boolean shiftStartCnySunday;

    public ChineseNewYearCalendar() {
        includeChineseNewYearEve = false;
        chineseNewYearEveLabel = "Chinese New Year's eve";
        includeChineseNewYear = true;
        chineseNewYearLabel = "Chinese New Year";
        includeChineseSecondDay = false;
        chineseSecondDayLabel = "Chinese New Year (2nd day)";
        includeChineseThirdDay = false;
        chineseThirdDayLabel = "Chinese New Year (3rd day)";
        shiftSundayHolidays = false;
        shiftStartCnySunday = false;
    }


    /**
     * Compute Chinese New Year days. To return a list of holidays.
     *
     * By default, it'll at least return the Chinese New Year holidays chosen
     * using the following options:
     *
     * - ``include_chinese_new_year_eve``
     * - ``include_chinese_new_year`` (on by default)
     * - ``include_chinese_second_day``
     *
     * If the ``shift_sunday_holidays`` option is on, the rules are the
     * following.
     *
     * - If the CNY1 falls on MON-FRI, there's not shift.
     * - If the CNY1 falls on SAT, the CNY2 is shifted to the Monday after.
     * - If the CNY1 falls on SUN, the CNY1 is shifted to the Monday after,
     *   and CNY2 is shifted to the Tuesday after.
     *
     * @param year
     * @return LocalDate
     */
    private List<Day> getChineseNewYear(int year) {
        ArrayList<Day> days = new ArrayList<>();

        LocalDate lunarFirstDay = LunarCalendar.lunar(year, 1, 1);
        // Chinese new year's eve
        if (this.includeChineseNewYearEve)
            days.add(new Day(lunarFirstDay.minusDays(1), this.chineseNewYearEveLabel));

        // Chinese new year (is included by default)
        if (this.includeChineseNewYear)
            days.add(new Day(lunarFirstDay, this.chineseNewYearLabel));

        LocalDate lunarSecondDay = null;
        if (this.includeChineseSecondDay) {
            lunarSecondDay = lunarFirstDay.plusDays(1);
            days.add(new Day(lunarSecondDay, this.chineseSecondDayLabel));
        }
        
        LocalDate lunarThirdDay = null;
        if (this.includeChineseThirdDay) {
            lunarThirdDay = lunarFirstDay.plusDays(2);
            days.add(new Day(lunarThirdDay, this.chineseThirdDayLabel));
        }

        if (this.shiftSundayHolidays) {
            if (lunarFirstDay.getDayOfWeek() == SUNDAY) {
                if (this.shiftStartCnySunday)
                    days.add(new Day(lunarFirstDay.minusDays(1), "Chinese Lunar New Year shift"));
                else {
                    LocalDate shiftDay;
                    if (this.includeChineseThirdDay)
                        shiftDay = lunarThirdDay;
                    else
                        shiftDay = lunarSecondDay;
                    days.add(new Day(shiftDay.plusDays(1), "Chinese Lunar New Year shift"));
                }
            }

            if (lunarSecondDay != null && lunarThirdDay != null) {
                if (lunarSecondDay.getDayOfWeek() == SUNDAY && this.includeChineseThirdDay)
                    days.add(new Day(lunarThirdDay.plusDays(1), "Chinese Lunar New Year shift"));
            }
        }

        return days;
    }


    public SortedSet<Day> getVariableDays(int year) {
        SortedSet<Day> days = this.getVariableDays(year);
        days.addAll(this.getChineseNewYear(year));

        return days;
    }


    /**
     * Taking a list of existing holidays, yield a list of 'shifted' days if
     * the holiday falls on SUN.
     * @param dates
     * @return SortedSet<Day>
     */
    private SortedSet<Day> getShiftedHolidays(SortedSet<Day> dates) {
        SortedSet<Day> shiftDates = new TreeSet<>(new DayComparator<>());

        for (Day date : dates) {
            if (date.getDate().getDayOfWeek() != SUNDAY)
                continue;

            shiftDates.add(date);
        }

        return shiftDates;
    }


    /**
     * Take into account the eventual shift to the next MON if any holiday
     *  falls on SUN.
     *
     * @param year
     * @return SortedSet<Day>
     */
    public SortedSet<Day> getCalendarHolidays(int year) {
        SortedSet<Day> days = super.getCalendarHolidays(year);
        if (this.shiftSundayHolidays) {
            for (Day dayShifted : getShiftedHolidays(days)) {
                days.add(dayShifted);
            }
        }

        return days;
    }
}
