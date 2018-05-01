package workalendar.util;

import java.time.LocalDate;
import java.time.Year;

public class Easter {
    public enum EASTER { JULIAN, ORTHODOX ,WESTERN }

    public static LocalDate sundayFor(Year year) {
        final int y = year.getValue();
        final int a = y % 19;
        final int b = y / 100;
        final int c = y % 100;
        final int d = b / 4;
        final int e = b % 4;
        final int f = (b + 8) / 25;
        final int g = (b - f + 1) / 3;
        final int h = (19 * a + b - d - g + 15) % 30;
        final int i = c / 4;
        final int k = c % 4;
        final int m = (32 + 2 * e + 2 * i - h - k) % 7;
        final int n = (a + 11 * h + 22 * m) / 451;
        final int month = (h + m - 7 * n + 114) / 31;
        final int day = (((h + m - (7 * n) + 114) % 31) + 1);
        return LocalDate.of(y, month, day);
    }


    /**
     *     This method was ported from the work done by GM Arts,
     *     on top of the algorithm by Claus Tondering, which was
     *     based in part on the algorithm of Ouding (1940), as
     *     quoted in "Explanatory Supplement to the Astronomical
     *     Almanac", P.  Kenneth Seidelmann, editor.
     *
     *     This algorithm implements three different easter
     *     calculation methods:
     *
     *     1 - Original calculation in Julian calendar, valid in
     *         dates after 326 AD
     *     2 - Original method, with date converted to Gregorian
     *         calendar, valid in years 1583 to 4099
     *     3 - Revised method, in Gregorian calendar, valid in
     *         years 1583 to 4099 as well
     *
     *     These methods are represented by the constants:
     *
     *     * ``EASTER_JULIAN   = 1``
     *     * ``EASTER_ORTHODOX = 2``
     *     * ``EASTER_WESTERN  = 3``
     *
     *     The default method is method 3.
     *
     *     More about the algorithm may be found at:
     *
     *     `GM Arts: Easter Algorithms <http://www.gmarts.org/index.php?go=415>`_
     *
     *     and
     *
     *     `The Calendar FAQ: Easter <https://www.tondering.dk/claus/cal/easter.php>`_
     *
     * @param year
     * @return LocalDatge
     */
    public static LocalDate easter(int year, EASTER method) {
        if (null == method)
            method = EASTER.WESTERN;

        // g - Golden year - 1
        // c - Century
        // h - (23 - Epact) mod 30
        // i - Number of days from March 21 to Paschal Full Moon
        // j - Weekday for PFM (0=Sunday, etc)
        // p - Number of days from March 21 to Sunday on or before PFM
        //     (-6 to 28 methods 1 & 3, to 56 for method 2)
        // e - Extra days to add for method 2 (converting Julian
        //     date to Gregorian date)
        int y = year;
        int g = y % 19;
        int e = 0;

        int i, j, c, h;
        if (EASTER.JULIAN.equals(method) || EASTER.ORTHODOX.equals(method)) {
            // Old method
            i = (19*g + 15) % 30;
            j = (y + y/4 + i) % 7;
            if (method == EASTER.ORTHODOX) {
                // Extra dates to convert Julian to Gregorian date
                e = 10;
                if (y > 1600) {
                    e = e + y / 100 - 16 - (y / 100 - 16) / 4;
                }
            }
        }
        else {
            // New method
            c = y / 100;
            h = (c - c / 4 - (8 * c + 13) / 25 + 19 * g + 15) % 30;
            i = h - (h / 28) * (1 - (h / 28) * (29 / (h + 1)) * ((21 - g) / 11));
            j = (y + y / 4 + i + 2 - c + c / 4) % 7;
        }

        // p can be from -6 to 56 corresponding to dates 22 March to 23 May
        // (later dates apply to method 2, although 23 May never actually occurs)
        int p = i - j + e;
        int d = 1 + (p + 27 + (p + 6)/40) % 31;
        int m = 3 + (p + 26)/30;

        return LocalDate.of(y, m, d);
    }
}