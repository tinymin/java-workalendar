package workalendar.core;

import java.time.LocalDate;

/**
 * Calendar ready to compute luncar calendar days
 */
public class LunarCalendar {
    public static LocalDate lunar(LocalDate localDate) {
        return LunarDate.LunarToSolar(localDate);
    }

    public static LocalDate lunar(int year, int month, int day) {
        return lunar(LocalDate.of(year, month, day));
    }
}
