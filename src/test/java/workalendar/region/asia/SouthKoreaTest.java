package workalendar.region.asia;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

public class SouthKoreaTest {
    @Test
    public void testYear2013() {
        SouthKorea cal = new SouthKorea();
        Set<LocalDate> holidays = cal.holidaysSet(2013);
        assertTrue(holidays.contains(LocalDate.of(2013, 1, 1)));    // new year
        assertTrue(holidays.contains(LocalDate.of(2013, 3, 1)));    // Independence day
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 5)));    // children's day
        assertTrue(holidays.contains(LocalDate.of(2013, 6, 6)));    // Memorial day
        assertTrue(holidays.contains(LocalDate.of(2013, 8, 15)));   // Liberation day
        assertTrue(holidays.contains(LocalDate.of(2013, 10, 3)));   // National Foundation Day
        assertTrue(holidays.contains(LocalDate.of(2013, 10, 9)));   // Hangul Day
        assertTrue(holidays.contains(LocalDate.of(2013, 12, 25)));  // Christmas

        // Variable days
        assertTrue(holidays.contains(LocalDate.of(2013, 2, 9)));
        assertTrue(holidays.contains(LocalDate.of(2013, 2, 10)));
        assertTrue(holidays.contains(LocalDate.of(2013, 2, 11)));
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 17)));
        assertTrue(holidays.contains(LocalDate.of(2013, 9, 18)));
        assertTrue(holidays.contains(LocalDate.of(2013, 9, 19)));
        assertTrue(holidays.contains(LocalDate.of(2013, 9, 20)));    
    }
}