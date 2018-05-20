package workalendar.region.asia;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class SouthKoreaTest {
    @Test
    public void testYear2013() {
        SouthKorea cal = new SouthKorea();
        int year = 2013;
        Set<LocalDate> holidays = cal.holidaysSet(year);
        assertTrue(holidays.contains(LocalDate.of(year, 1, 1)));    // new year
        assertTrue(holidays.contains(LocalDate.of(year, 3, 1)));    // Independence day
        assertTrue(holidays.contains(LocalDate.of(year, 5, 5)));    // children's day
        assertTrue(holidays.contains(LocalDate.of(year, 6, 6)));    // Memorial day
        assertTrue(holidays.contains(LocalDate.of(year, 8, 15)));   // Liberation day
        assertTrue(holidays.contains(LocalDate.of(year, 10, 3)));   // National Foundation Day
        assertTrue(holidays.contains(LocalDate.of(year, 10, 9)));   // Hangul Day
        assertTrue(holidays.contains(LocalDate.of(year, 12, 25)));  // Christmas

        // Variable days
        assertTrue(holidays.contains(LocalDate.of(year, 2, 9)));
        assertTrue(holidays.contains(LocalDate.of(year, 2, 10)));
        assertTrue(holidays.contains(LocalDate.of(year, 2, 11)));
        assertTrue(holidays.contains(LocalDate.of(year, 5, 17)));
        assertTrue(holidays.contains(LocalDate.of(year, 9, 18)));
        assertTrue(holidays.contains(LocalDate.of(year, 9, 19)));
        assertTrue(holidays.contains(LocalDate.of(year, 9, 20)));
    }
}