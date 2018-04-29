package net.suncheol.workalendar.region;

import org.junit.Test;
import workalendar.core.Day;
import workalendar.region.usa.UnitedStates;

import java.time.LocalDate;
import java.util.SortedSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitedStatesTest {
    UnitedStates usaCalendar = new UnitedStates();

//    @Test
//    public void testMartinLutherKingDay() {
//        // All States observe this day, but it started in 1985 only.
//        Set<LocalDate> holidays = this.usaCalendar.holidaysSet(2013);
//        mlk_day = this.usaCalendar.getMartinLutherKingDate(2013);
//        assertEquals(date(2013, 1, 21), mlk_day);
//        assertTrue(mlk_day, holidays);
//
//        holidays = this.usaCalendar.holidays_set(2014);
//        mlk_day = this.usaCalendar.get_martin_luther_king_date(2014);
//        assertEquals(date(2014, 1, 20), mlk_day);
//        assertTrue(mlk_day, holidays);
//
//        // Shifted in 2015
//        holidays = this.usaCalendar.holidays_set(2015);
//        mlk_day = this.usaCalendar.get_martin_luther_king_date(2015);
//        assertEquals(date(2015, 1, 19), mlk_day);
//        assertTrue(mlk_day, holidays);
//
//        // Let's get into the past
//        holidays = this.usaCalendar.holidays_set(1986);
//        mlk_day = this.usaCalendar.get_martin_luther_king_date(1986);
//        assertEquals(date(1986, 1, 20), mlk_day);
//        assertTrue(mlk_day, holidays);
//
//        holidays = this.usaCalendar.holidays_set(1985);
//        mlk_day = this.usaCalendar.get_martin_luther_king_date(1985);
//        assertEquals(date(1985, 1, 21), mlk_day);
//        assertTrue(mlk_day, holidays);
//
//        // No MLK Day before 1985
//        // 3rd Monday of January was the 16th
//        holidays = this.usaCalendar.holidays_set(1984);
//        assertNotIn(date(1984, 1, 16), holidays);
//        with assertRaises (ValueError):
//        this.usaCalendar.get_martin_luther_king_date(1984);
//    }
//
//
//    @Test
//    public void test_mlk_label() {
//        _, label = this.usaCalendar.get_martin_luther_king_day(2017);
//        assertEquals(label, "Birthday of Martin Luther King, Jr.");
//    }
//
//
//    @Test
//    public void test_federal_year_2013() {
//        holidays = this.usaCalendar.holidays_set(2013);
//        assertTrue(date(2013, 1, 1), holidays);   // New Year
//        assertTrue(date(2013, 5, 27), holidays);   // Memorial day
//        assertTrue(date(2013, 7, 4), holidays);   // Nation day
//        assertTrue(date(2013, 9, 2), holidays);   // Labour day
//        assertTrue(date(2013, 11, 11), holidays);   // Armistice
//        assertTrue(date(2013, 11, 28), holidays);   // Thanskgiving
//        assertTrue(date(2013, 12, 25), holidays);   // Christmas
//    }
//
//
//    @Test
//    public void test_presidential_year() {
//        assertTrue(UnitedStates.is_presidential_year(2012));
//        assertFalse(UnitedStates.is_presidential_year(2013));
//        assertFalse(UnitedStates.is_presidential_year(2014));
//        assertFalse(UnitedStates.is_presidential_year(2015));
//        assertTrue(UnitedStates.is_presidential_year(2016));
//    }
//
//    
//    @Test
//    public void test_election_day() {
//        // Election day is:
//        // the Tuesday next after the first Monday in the month of November
//        assertEquals(date(2013, 11, 5), this.usaCalendar.get_election_date(2013));
//        assertEquals(date(2014, 11, 4), this.usaCalendar.get_election_date(2014));
//        assertEquals(date(2015, 11, 3), this.usaCalendar.get_election_date(2015));
//        assertEquals(date(2016, 11, 8), this.usaCalendar.get_election_date(2016));
//        assertEquals(date(2017, 11, 7), this.usaCalendar.get_election_date(2017));
//        assertEquals(date(2018, 11, 6), this.usaCalendar.get_election_date(2018));
//        assertEquals(date(2019, 11, 5), this.usaCalendar.get_election_date(2019));
//        assertEquals(date(2020, 11, 3), this.usaCalendar.get_election_date(2020));
//    }
//
//
//    @Test
//    public void test_election_day_label() {
//        _, label = this.usaCalendar.get_election_day(2017);
//        assertEquals(label, "Election Day");
//    }
//
//    
//    @Test
//    public void test_federal_year_2014() {
//        holidays = this.usaCalendar.holidays_set(2014);
//        assertTrue(date(2014, 1, 1), holidays);   // New Year
//        assertTrue(date(2014, 5, 26), holidays);   // Memorial day
//        assertTrue(date(2014, 7, 4), holidays);   // Nation day
//        assertTrue(date(2014, 9, 1), holidays);   // Labour day
//        assertTrue(date(2014, 11, 11), holidays);   // Armistice
//        assertTrue(date(2014, 11, 27), holidays);   // Thanskgiving
//        assertTrue(date(2014, 12, 25), holidays);   // XMas
//    }
//
//    
//    @Test
//    public void test_federal_year_2015() {
//        holidays = this.usaCalendar.holidays_set(2015);
//        assertTrue(date(2015, 1, 1), holidays);   // New Year
//        assertTrue(date(2015, 5, 25), holidays);   // Memorial day
//        assertTrue(date(2015, 7, 4), holidays);   // Nation day
//        assertTrue(date(2015, 9, 7), holidays);   // Labour day
//        assertTrue(date(2015, 11, 11), holidays);   // Armistice
//        assertTrue(date(2015, 11, 26), holidays);   // Thanskgiving
//        assertTrue(date(2015, 12, 25), holidays);   // XMas
//    }
//
//    
//    @Test
//    public void test_federal_year_2017() {
//        holidays = this.usaCalendar.holidays_set(2017);
//        assertNotIn(date(2017, 12, 27), holidays);   // XMas
//    }
//
//    
//    @Test
//    public void test_columbus_day() {
//        holidays = this.usaCalendar.holidays_set(2017);
//        // Columbus Day is included here
//        assertTrue(date(2017, 10, 9), holidays);
//    }
//
//    
//    @Test
//    public void test_columbus_day_label() {
//        _, label = this.usaCalendar.get_columbus_day(2017);
//        assertEquals(label, "Columbus Day");
//    }
//
//    
//    @Test
//    public void test_presidential_day() {
//        // Washington's birthday, or sometimes called otherwise, may not
//        // be included.
//        holidays = this.usaCalendar.holidays_set(2017);
//        day, _ = this.usaCalendar.get_presidents_day(2017);
//        // Washington's birthday is included here
//        assertTrue(day, holidays);
//    }
//
//
//    @Test
//    public void test_president_day_label() {
//        _, label = this.usaCalendar.get_presidents_day(2017);
//        assertEquals(label, "Washington's Birthday");
//    }
//
//
//    @Test
//    public void test_get_inauguration_date() {
//        assertEquals(
//                date(2017, 1, 20), this.usaCalendar.get_inauguration_date(2017));
//        // Not an "inauguration day" year
//        with assertRaises (ValueError):
//        this.usaCalendar.get_inauguration_date(2016);
//        with assertRaises (ValueError):
//        this.usaCalendar.get_inauguration_date(2015);
//        with assertRaises (ValueError):
//        this.usaCalendar.get_inauguration_date(2014);
//        // Shifted to MON, since the 20th was on SUN
//        assertEquals(
//                date(2013, 1, 21), this.usaCalendar.get_inauguration_date(2013));
//        // 2009, back to normal
//        assertEquals(
//                date(2009, 1, 20), this.usaCalendar.get_inauguration_date(2009));
//    }
//
//
//    @Test
//    public void test_inauguration_day() {
//        // NOTE: 2013 test is not relevant, it's the same day as MLK day.
//        // NOTE: 1985 test is not relevant, it's the same day as MLK day.
//        // By default, it's not a public holiday
//        assertNotIn(
//                this.usaCalendar.get_inauguration_date(2017),
//                this.usaCalendar.holidays_set(2017);
//        );
//        assertNotIn(
//                this.usaCalendar.get_inauguration_date(2009),
//                this.usaCalendar.holidays_set(2009);
//        );
//        assertNotIn(
//                this.usaCalendar.get_inauguration_date(1957),
//                this.usaCalendar.holidays_set(1957);
//        );
//    }
//
//
//    @Test
//    public void test_election_day_inclusion() {
//        // By default, election day is not included
//        for year in range(2013, 2020):
//        holidays = this.usaCalendar.holidays_set(year);
//        assertNotIn(this.usaCalendar.get_election_date(year), holidays);
//    }
//
//
//    @Test
//    public void test_thanksgiving_friday_label() {
//        _, label = this.usaCalendar.get_thanksgiving_friday(2017);
//        assertEquals(label, "Thanksgiving Friday");
//    }
//
//
//    @Test
//    public void test_national_memorial_label() {
//        _, label = this.usaCalendar.get_national_memorial_day(2017);
//        assertEquals(label, "Memorial Day");
//    }
//
//
//    @Test
//    public void test_veterans_label() {
//        _, label = this.usaCalendar.get_veterans_day(2017);
//        assertEquals(label, "Veterans Day");
//    }
//
//
//    @Test
//    public void test_mardi_gras() {
//        year = 2017;
//        day, _ = this.usaCalendar.get_mardi_gras(year);
//        holidays = this.usaCalendar.holidays_set(year);
//        assertNotIn(day, holidays);
//    }
    /****************************************************************************************************/
    /****************************************************************************************************/

    
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
        SortedSet<Day> holidays = usaCalendar.holidays(2008);
        assertFalse(holidays.contains(LocalDate.of(2008, 1, 20)));

        holidays = usaCalendar.holidays(2009);
        assertTrue(holidays.contains(LocalDate.of(2009, 1, 20)));

        // case when inauguration day is a sunday
        holidays = usaCalendar.holidays(1985);
        assertFalse(holidays.contains(LocalDate.of(1985, 1, 20)));
        assertTrue(holidays.contains(LocalDate.of(1985, 1, 21)));
    }
}