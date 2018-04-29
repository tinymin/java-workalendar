package workalendar.core;

import workalendar.util.Easter;

import java.time.LocalDate;
import java.time.Year;
import java.util.SortedSet;

// TODO WesternCalendar 컨버팅
public class ChristianMixin extends Workalendar {
    @Override
    public SortedSet<Day> getVariableDays(int year) {
        return null;
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
}
