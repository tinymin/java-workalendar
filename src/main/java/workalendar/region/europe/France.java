package workalendar.region.europe;

import workalendar.core.model.Day;
import workalendar.core.WesternCalendar;

import java.time.LocalDate;
import java.util.SortedSet;


// TODO France 캘런더 재작업 필요
/**
 * France calendar
 */
public class France extends WesternCalendar {
    public France() {
        super();
    }


//    public LocalDate getAscensionThursday(int year) {
//        LocalDate easter = this.getEasterSunday(year);
//        return easter.plusDays(39);
//    }
//
//
//    public LocalDate getPentecoteMonday(int year) {
//        LocalDate easter = this.getEasterSunday(year);
//        return easter.plusDays(50);
//    }


    @Override
    public SortedSet<Day> getCalendarHolidays(int year) {
        SortedSet<Day> days = super.getCalendarHolidays(year);

        days.add(new Day(LocalDate.of(year, 5, 1), "")); // Labour day
        days.add(new Day(LocalDate.of(year, 5, 8), "")); // 1939-45 victory
        days.add(new Day(LocalDate.of(year, 7, 14), "")); // National day
        days.add(new Day(LocalDate.of(year, 8, 15), "")); // Assomption
        days.add(new Day(LocalDate.of(year, 11, 1), "")); // Toussaint
        days.add(new Day(LocalDate.of(year, 11, 11), "")); // Armistice
        days.add(new Day(LocalDate.of(year, 12, 25), "")); // Christmas
//        days.add(new Day(this.getEasterMonday(year), ""));
//        days.add(new Day(this.getAscensionThursday(year), ""));
//        days.add(new Day(this.getPentecoteMonday(year), ""));

        return days;
    }
}
