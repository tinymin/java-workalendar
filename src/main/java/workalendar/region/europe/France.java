package workalendar.region.europe;

import workalendar.core.WesternCalendar;

import java.time.LocalDate;
import java.util.Set;


/**
 * France calendar
 */
public class France extends WesternCalendar {
    public France() {
        super();
    }


    public LocalDate getAscensionThursday(int year) {
        LocalDate easter = this.getEasterSunday(year);
        return easter.plusDays(39);
    }


    public LocalDate getPentecoteMonday(int year) {
        LocalDate easter = this.getEasterSunday(year);
        return easter.plusDays(50);
    }


    @Override
    public Set<LocalDate> getCalendarHolidays(int year) {
        Set<LocalDate> days = super.getCalendarHolidays(year);

        days.add(LocalDate.of(year, 5, 1)); // Labour day
        days.add(LocalDate.of(year, 5, 8)); // 1939-45 victory
        days.add(LocalDate.of(year, 7, 14)); // National day
        days.add(LocalDate.of(year, 8, 15)); // Assomption
        days.add(LocalDate.of(year, 11, 1)); // Toussaint
        days.add(LocalDate.of(year, 11, 11)); // Armistice
        days.add(LocalDate.of(year, 12, 25)); // Christmas
        days.add(this.getEasterMonday(year));
        days.add(this.getAscensionThursday(year));
        days.add(this.getPentecoteMonday(year));

        return days;
    }
}
