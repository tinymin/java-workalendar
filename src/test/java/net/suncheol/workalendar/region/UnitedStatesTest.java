package net.suncheol.workalendar.region;

import org.junit.Test;
import workalendar.region.america.UnitedStates;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitedStatesTest {
    UnitedStates usaCalendar = new UnitedStates();

    @Test
    public void testYear2013() {
        Set<LocalDate> holidays = usaCalendar.holidays(2013);
        assertTrue(holidays.contains(LocalDate.of(2013, 1, 1)));
        assertTrue(holidays.contains(LocalDate.of(2013, 7, 4))); // Nation day
        assertTrue(holidays.contains(LocalDate.of(2013, 11, 11))); // Armistice
        assertTrue(holidays.contains(LocalDate.of(2013, 12, 25))); // Christmas

        // Variable days
        assertTrue(holidays.contains(LocalDate.of(2013, 1, 21))); // Martin Luther King
        assertTrue(holidays.contains(LocalDate.of(2013, 2, 18))); // Washington's bday
        assertTrue(holidays.contains(LocalDate.of(2013, 5, 27))); // Memorial day
        assertTrue(holidays.contains(LocalDate.of(2013, 9, 2))); // Labour day
        assertTrue(holidays.contains(LocalDate.of(2013, 10, 14))); // Colombus
        assertTrue(holidays.contains(LocalDate.of(2013, 11, 28))); // Thanskgiving
    }

    @Test
    public void testPresidentialYear() {
        assertTrue(UnitedStates.isPresidentialYear(2012));
        assertFalse(UnitedStates.isPresidentialYear(2013));
        assertFalse(UnitedStates.isPresidentialYear(2014));
        assertFalse(UnitedStates.isPresidentialYear(2015));
        assertTrue(UnitedStates.isPresidentialYear(2016));
    }

    @Test
    public void testInaugurationDay() {
        Set<LocalDate> holidays = usaCalendar.holidays(2008);
        assertFalse(holidays.contains(LocalDate.of(2008, 1, 20)));

        holidays = usaCalendar.holidays(2009);
        assertTrue(holidays.contains(LocalDate.of(2009, 1, 20)));

        // case when inauguration day is a sunday
        holidays = usaCalendar.holidays(1985);
        assertFalse(holidays.contains(LocalDate.of(1985, 1, 20)));
        assertTrue(holidays.contains(LocalDate.of(1985, 1, 21)));
    }
}