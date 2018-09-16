package workalendar.region.asia;

import workalendar.core.ChineseNewYearCalendar;
import workalendar.core.WesternCalendar;
import workalendar.core.model.Day;
import workalendar.core.model.FixedDay;

import java.util.Arrays;
import java.util.SortedSet;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static workalendar.core.LunarCalendar.lunar;


/**
 * South Korea
 */
public class SouthKorea implements WesternCalendar, ChineseNewYearCalendar {
    public SouthKorea() {
        WEEKEND_DAYS.addAll(Arrays.asList(SATURDAY, SUNDAY));

        this.FIXED_HOLIDAYS.add(new FixedDay(1, 1, "New Year"));
        this.FIXED_HOLIDAYS.add(new FixedDay(3, 1, "Independence Day"));
        this.FIXED_HOLIDAYS.add(new FixedDay(5, 5, "Children's Day"));
        this.FIXED_HOLIDAYS.add(new FixedDay(6, 6, "Memorial Day"));
        this.FIXED_HOLIDAYS.add(new FixedDay(8, 15, "Liberation Day"));
        this.FIXED_HOLIDAYS.add(new FixedDay(10, 3, "National Foundation Day"));
        this.FIXED_HOLIDAYS.add(new FixedDay(10, 9, "Hangul Day"));
        this.FIXED_HOLIDAYS.add(new FixedDay(12, 25, "Christmas Day"));

//        chineseNewYearLabel = "Korean New Year's Day";
//        includeChineseNewYearEve(true);
//        chineseNewYearEveLabel("Korean New Year's Day");
//        includeChineseSecondDay(true);
//        chineseSecondDayLabel("Korean New Year's Day");
    }

    @Override
    public SortedSet<Day> getVariableDays(int year) {
        SortedSet<Day> days = WesternCalendar.super.getVariableDays(year);
        days.addAll(ChineseNewYearCalendar.super.getVariableDays(year));

        days.addAll(Arrays.asList(
                 new Day(lunar(year, 4, 8), "Buddha's Birthday")
                // Midautumn Festival (3 days))
                ,new Day(lunar(year, 8, 14), "Midautumn Festival")
                ,new Day(lunar(year, 8, 15), "Midautumn Festival")
                ,new Day(lunar(year, 8, 16), "Midautumn Festival")
        ));

        return days;
    }


    @Override
    public boolean isShiftNewYearsDay() {
        return true;
    }
}
