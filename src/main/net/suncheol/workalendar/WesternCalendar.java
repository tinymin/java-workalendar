package net.suncheol.workalendar;

import java.time.LocalDate;

/**
 * General usage calendar for Western countries.
 *
 * (chiefly Europe and Northern America)
 */
class WesternCalendar {

    /**
     * European countries have at least these 2 days as holidays in common
     */
    public LocalDate getCalendarHolidays(int year) {
        throw new UnsupportedOperationException();
    }

    /**
     * Week-end days are SATurday and SUNday.
     */
    public LocalDate getWeekendDays() {
        throw new UnsupportedOperationException();
    }


}
