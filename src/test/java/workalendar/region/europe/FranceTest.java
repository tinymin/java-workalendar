package workalendar.region.europe;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

public class FranceTest {
    France cal = new France();

    @Test
    public void testYear2013() {
        Set<LocalDate> holidays = cal.holidays(2013);

        assertTrue(holidays.contains(LocalDate.of(2013, 1, 1)));    // new year
        assertTrue(holidays.contains(LocalDate.of(2013, 4, 1)));    // easter
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 1)));    // labour day
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 8)));    // 39-45
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 9)));    // Ascension
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 20)));   // Pentecote
        assertTrue(holidays.contains(LocalDate.of(2013, 7, 14)));   // Nation day
        assertTrue(holidays.contains(LocalDate.of(2013, 8, 15)));   // Assomption
        assertTrue(holidays.contains(LocalDate.of(2013, 11, 1)));   // Toussaint
        assertTrue(holidays.contains(LocalDate.of(2013, 11, 11)));  // Armistice
        assertTrue(holidays.contains(LocalDate.of(2013, 12, 25)));  // Christmas
    }


    @Test
    public void testWorkdays() {
        assertFalse(cal.isWorkday(LocalDate.of(2013, 1, 1)));   // holiday
        assertFalse(cal.isWorkday(LocalDate.of(2013, 1, 5)));   // saturday
        assertFalse(cal.isWorkday(LocalDate.of(2013, 1, 6)));   // sunday
        assertTrue(cal.isWorkday(LocalDate.of(2013, 1, 7)));    // monday
    }


    @Test
    public void testBusinessDaysComputations() {
        LocalDate day = LocalDate.of(2013, 10, 30);
        assertEquals(cal.addWorkdays(day, 0), LocalDate.of(2013, 10, 30));
        assertEquals(cal.addWorkdays(day, 1), LocalDate.of(2013, 10, 31));
        assertEquals(cal.addWorkdays(day, 2), LocalDate.of(2013, 11, 4));
        assertEquals(cal.addWorkdays(day, 3), LocalDate.of(2013, 11, 5));
    }
}