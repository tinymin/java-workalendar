package workalendar.core;

import lombok.Getter;

import java.time.LocalDate;

/**
 * LunarDateCandidate1.java
 * Copyright (c) 1997-2002 by Dr. Herong Yang
 * Valid for 200 years: from Gregorian year 1901 to 2100
 * Not sychronized.
 */

@Deprecated
class LunarDateCandidate1 {
    private static char[] daysInGregorianMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] monthNames =
            {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static String[] stemNames =
            {"Wood", "Wood", "Fire", "Fire", "Earth", "Earth",
                    "Metal", "Metal", "Water", "Water"};
    private static String[] branchNames =
            {"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake",
                    "Horse", "Sheep", "Monkey", "Rooster", "Dog", "Boar"};
    private static char[] chineseMonths = {
            //Chinese month map, 2 bytes per year, from 1900 to 2100, 402 bytes.
            //The first 4 bits represents the leap month of the year.
            //The rest 12 bits are flags indicate if the corresponding month
            //is a 29-day month. 2 bytes are stored in low-high order.
            0x00, 0x04, 0xad, 0x08, 0x5a, 0x01, 0xd5, 0x54, 0xb4, 0x09, 0x64, 0x05, 0x59, 0x45,
            0x95, 0x0a, 0xa6, 0x04, 0x55, 0x24, 0xad, 0x08, 0x5a, 0x62, 0xda, 0x04, 0xb4, 0x05,
            0xb4, 0x55, 0x52, 0x0d, 0x94, 0x0a, 0x4a, 0x2a, 0x56, 0x02, 0x6d, 0x71, 0x6d, 0x01,
            0xda, 0x02, 0xd2, 0x52, 0xa9, 0x05, 0x49, 0x0d, 0x2a, 0x45, 0x2b, 0x09, 0x56, 0x01,
            0xb5, 0x20, 0x6d, 0x01, 0x59, 0x69, 0xd4, 0x0a, 0xa8, 0x05, 0xa9, 0x56, 0xa5, 0x04,
            0x2b, 0x09, 0x9e, 0x38, 0xb6, 0x08, 0xec, 0x74, 0x6c, 0x05, 0xd4, 0x0a, 0xe4, 0x6a,
            0x52, 0x05, 0x95, 0x0a, 0x5a, 0x42, 0x5b, 0x04, 0xb6, 0x04, 0xb4, 0x22, 0x6a, 0x05,
            0x52, 0x75, 0xc9, 0x0a, 0x52, 0x05, 0x35, 0x55, 0x4d, 0x0a, 0x5a, 0x02, 0x5d, 0x31,
            0xb5, 0x02, 0x6a, 0x8a, 0x68, 0x05, 0xa9, 0x0a, 0x8a, 0x6a, 0x2a, 0x05, 0x2d, 0x09,
            0xaa, 0x48, 0x5a, 0x01, 0xb5, 0x09, 0xb0, 0x39, 0x64, 0x05, 0x25, 0x75, 0x95, 0x0a,
            0x96, 0x04, 0x4d, 0x54, 0xad, 0x04, 0xda, 0x04, 0xd4, 0x44, 0xb4, 0x05, 0x54, 0x85,
            0x52, 0x0d, 0x92, 0x0a, 0x56, 0x6a, 0x56, 0x02, 0x6d, 0x02, 0x6a, 0x41, 0xda, 0x02,
            0xb2, 0xa1, 0xa9, 0x05, 0x49, 0x0d, 0x0a, 0x6d, 0x2a, 0x09, 0x56, 0x01, 0xad, 0x50,
            0x6d, 0x01, 0xd9, 0x02, 0xd1, 0x3a, 0xa8, 0x05, 0x29, 0x85, 0xa5, 0x0c, 0x2a, 0x09,
            0x96, 0x54, 0xb6, 0x08, 0x6c, 0x09, 0x64, 0x45, 0xd4, 0x0a, 0xa4, 0x05, 0x51, 0x25,
            0x95, 0x0a, 0x2a, 0x72, 0x5b, 0x04, 0xb6, 0x04, 0xac, 0x52, 0x6a, 0x05, 0xd2, 0x0a,
            0xa2, 0x4a, 0x4a, 0x05, 0x55, 0x94, 0x2d, 0x0a, 0x5a, 0x02, 0x75, 0x61, 0xb5, 0x02,
            0x6a, 0x03, 0x61, 0x45, 0xa9, 0x0a, 0x4a, 0x05, 0x25, 0x25, 0x2d, 0x09, 0x9a, 0x68,
            0xda, 0x08, 0xb4, 0x09, 0xa8, 0x59, 0x54, 0x03, 0xa5, 0x0a, 0x91, 0x3a, 0x96, 0x04,
            0xad, 0xb0, 0xad, 0x04, 0xda, 0x04, 0xf4, 0x62, 0xb4, 0x05, 0x54, 0x0b, 0x44, 0x5d,
            0x52, 0x0a, 0x95, 0x04, 0x55, 0x22, 0x6d, 0x02, 0x5a, 0x71, 0xda, 0x02, 0xaa, 0x05,
            0xb2, 0x55, 0x49, 0x0b, 0x4a, 0x0a, 0x2d, 0x39, 0x36, 0x01, 0x6d, 0x80, 0x6d, 0x01,
            0xd9, 0x02, 0xe9, 0x6a, 0xa8, 0x05, 0x29, 0x0b, 0x9a, 0x4c, 0xaa, 0x08, 0xb6, 0x08,
            0xb4, 0x38, 0x6c, 0x09, 0x54, 0x75, 0xd4, 0x0a, 0xa4, 0x05, 0x45, 0x55, 0x95, 0x0a,
            0x9a, 0x04, 0x55, 0x44, 0xb5, 0x04, 0x6a, 0x82, 0x6a, 0x05, 0xd2, 0x0a, 0x92, 0x6a,
            0x4a, 0x05, 0x55, 0x0a, 0x2a, 0x4a, 0x5a, 0x02, 0xb5, 0x02, 0xb2, 0x31, 0x69, 0x03,
            0x31, 0x73, 0xa9, 0x0a, 0x4a, 0x05, 0x2d, 0x55, 0x2d, 0x09, 0x5a, 0x01, 0xd5, 0x48,
            0xb4, 0x09, 0x68, 0x89, 0x54, 0x0b, 0xa4, 0x0a, 0xa5, 0x6a, 0x95, 0x04, 0xad, 0x08,
            0x6a, 0x44, 0xda, 0x04, 0x74, 0x05, 0xb0, 0x25, 0x54, 0x03
    };
    // Base date: 01-Jan-1901, 4598/11/11 in Chinese calendar
    private static int baseYear = 1901;
    private static int baseMonth = 1;
    private static int baseDate = 1;
    private static int baseIndex = 0;
    private static int baseChineseYear = 4598 - 1;
    private static int baseChineseMonth = 11;
    private static int baseChineseDate = 11;
    private static int[] bigLeapMonthYears = {
            // The leap months in the following years have 30 days
            6, 14, 19, 25, 33, 36, 38, 41, 44, 52,
            55, 79, 117, 136, 147, 150, 155, 158, 185, 193
    };
    private static char[][] sectionalTermMap = {
            {7, 6, 6, 6, 6, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5},   // Jan
            {5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 3, 4, 4, 3, 3, 3},   // Feb
            {6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5}, // Mar
            {5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 4, 5}, // Apr
            {6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5}, // May
            {6, 6, 7, 7, 6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5},
            {7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 6, 6, 6, 7, 7}, // Jul
            {8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 7},
            {8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 7}, // Sep
            {9, 9, 9, 9, 8, 9, 9, 9, 8, 8, 9, 9, 8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 8}, // Oct
            {8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 7}, // Nov
            {7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 6, 6, 6, 7, 7}  // Dec
    };
    private static char[][] sectionalTermYear = {
            {13, 49, 85, 117, 149, 185, 201, 250, 250}, // Jan
            {13, 45, 81, 117, 149, 185, 201, 250, 250}, // Feb
            {13, 48, 84, 112, 148, 184, 200, 201, 250}, // Mar
            {13, 45, 76, 108, 140, 172, 200, 201, 250}, // Apr
            {13, 44, 72, 104, 132, 168, 200, 201, 250}, // May
            {5, 33, 68, 96, 124, 152, 188, 200, 201}, // Jun
            {29, 57, 85, 120, 148, 176, 200, 201, 250}, // Jul
            {13, 48, 76, 104, 132, 168, 196, 200, 201}, // Aug
            {25, 60, 88, 120, 148, 184, 200, 201, 250}, // Sep
            {16, 44, 76, 108, 144, 172, 200, 201, 250}, // Oct
            {28, 60, 92, 124, 160, 192, 200, 201, 250}, // Nov
            {17, 53, 85, 124, 156, 188, 200, 201, 250}  // Dec
    };
    private static char[][] principleTermMap = {
            {21, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 20, 20, 20, 20, 20, 19,
                    20, 20, 20, 19, 19, 20},
            {20, 19, 19, 20, 20, 19, 19, 19, 19, 19, 19, 19, 19, 18, 19, 19, 19, 18, 18, 19, 19, 18,
                    18, 18, 18, 18, 18, 18},
            {21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20,
                    20, 20, 19, 20, 20, 20, 20},
            {20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20, 20, 20, 19, 20, 20, 20, 19, 19,
                    20, 20, 19, 19, 19, 20, 20},
            {21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20,
                    21, 21, 20, 20, 20, 21, 21},
            {22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 20, 21,
                    21, 21, 20, 20, 21, 21, 21},
            {23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22,
                    22, 23, 22, 22, 22, 22, 23},
            {23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22,
                    23, 23, 22, 22, 22, 23, 23},
            {23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22,
                    23, 23, 22, 22, 22, 23, 23},
            {24, 24, 24, 24, 23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23,
                    23, 23, 22, 22, 23, 23, 23},
            {23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22,
                    22, 22, 21, 21, 22, 22, 22},
            {22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21,
                    21, 22, 21, 21, 21, 21, 22}
    };
    private static char[][] principleTermYear = {
            {13, 45, 81, 113, 149, 185, 201},     // Jan
            {21, 57, 93, 125, 161, 193, 201},     // Feb
            {21, 56, 88, 120, 152, 188, 200, 201}, // Mar
            {21, 49, 81, 116, 144, 176, 200, 201}, // Apr
            {17, 49, 77, 112, 140, 168, 200, 201}, // May
            {28, 60, 88, 116, 148, 180, 200, 201}, // Jun
            {25, 53, 84, 112, 144, 172, 200, 201}, // Jul
            {29, 57, 89, 120, 148, 180, 200, 201}, // Aug
            {17, 45, 73, 108, 140, 168, 200, 201}, // Sep
            {28, 60, 92, 124, 160, 192, 200, 201}, // Oct
            {16, 44, 80, 112, 148, 180, 200, 201}, // Nov
            {17, 53, 88, 120, 156, 188, 200, 201}  // Dec
    };

    @Getter
    private LocalDate gregorianDate;

    @Getter
    private LocalDate lunarDate;


    public LunarDateCandidate1() {
        this(LocalDate.now());
    }

    public LunarDateCandidate1(LocalDate localDate) {
        setGregorian(localDate);
        this.computeChineseFields();
    }

    public static boolean isGregorianLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0) isLeap = true;
        if (year % 100 == 0) isLeap = false;
        if (year % 400 == 0) isLeap = true;
        return isLeap;
    }

    public static int daysInGregorianMonth(int y, int m) {
        int d = daysInGregorianMonth[m - 1];
        if (m == 2 && isGregorianLeapYear(y)) d++; // Leap year adjustment
        return d;
    }

    public static int daysInChineseMonth(int y, int m) {
        // Regular month: m > 0
        // Leap month: m < 0
        int index = y - baseChineseYear + baseIndex;
        int v = 0;
        int l = 0;
        int d = 30; // normal month
        if (1 <= m && m <= 8) {
            v = chineseMonths[2 * index];
            l = m - 1;
            if (((v >> l) & 0x01) == 1) d = 29;
        } else if (9 <= m && m <= 12) {
            v = chineseMonths[2 * index + 1];
            l = m - 9;
            if (((v >> l) & 0x01) == 1) d = 29;
        } else { // leap month
            v = chineseMonths[2 * index + 1];
            v = (v >> 4) & 0x0F;
            if (v != Math.abs(m)) {
                d = 0; // wrong m specified
            } else {
                d = 29;
                for (int i = 0; i < bigLeapMonthYears.length; i++) {
                    if (bigLeapMonthYears[i] == index) {
                        d = 30;
                        break;
                    }
                }
            }
        }
        return d;
    }

    public static int nextChineseMonth(int y, int m) {
        int n = Math.abs(m) + 1; // normal behavior
        if (m > 0) {
            // need to find out if we are in a leap year or not
            int index = y - baseChineseYear + baseIndex;
            int v = chineseMonths[2 * index + 1];
            v = (v >> 4) & 0x0F;
            if (v == m) n = -m; // next month is a leap month
        }
        if (n == 13) n = 1; //roll into next year
        return n;
    }

    private void setGregorian(LocalDate date) {
        gregorianDate = date;
        lunarDate = LocalDate.of(1,1,1);
    }

    private int computeChineseFields() {
        // Gregorian year out of the computation range
        if (gregorianDate.getYear()<1901 || gregorianDate.getYear()>2100) return 1;
        int startYear = baseYear;
        int startMonth = baseMonth;
        int startDate = baseDate;
        int chineseYear = baseChineseYear;
        int chineseMonth = baseChineseMonth;
        int chineseDate = baseChineseDate;
        // Switching to the second base to reduce the calculation process
        // Second base date: 01-Jan-2000, 4697/11/25 in Chinese calendar
        if (gregorianDate.getYear() >= 2000) {
            startYear = baseYear + 99;
            startMonth = 1;
            startDate = 1;
            chineseYear = baseChineseYear + 99;
            chineseMonth = 11;
            chineseDate = 25;
        }
        // Calculating the number of days
        //    between the start date and the current date
        // The following algorithm only works
        //    for startMonth = 1 and startDate = 1
        int daysDiff = 0;
        for (int i=startYear; i<gregorianDate.getYear(); i++) {
            daysDiff += 365;
            if (isGregorianLeapYear(i)) daysDiff += 1; // leap year
        }
        for (int i=startMonth; i<gregorianDate.getMonthValue(); i++) {
            daysDiff += daysInGregorianMonth(gregorianDate.getYear(),i);
        }
        daysDiff += gregorianDate.getDayOfMonth() - startDate;

        // Adding that number of days to the Chinese date
        // Then bring Chinese date into the correct range.
        //    one Chinese month at a time
        chineseDate += daysDiff;
        int lastDate = daysInChineseMonth(chineseYear, chineseMonth);
        int nextMonth = nextChineseMonth(chineseYear, chineseMonth);
        while (chineseDate>lastDate) {
            if (Math.abs(nextMonth)<Math.abs(chineseMonth)) chineseYear++;
            chineseMonth = nextMonth;
            chineseDate -= lastDate;
            lastDate = daysInChineseMonth(chineseYear, chineseMonth);
            nextMonth = nextChineseMonth(chineseYear, chineseMonth);
        }

        lunarDate = LocalDate.of(chineseYear, chineseMonth, chineseDate);
        return 0;
    }


    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Gregorian Year: " + gregorianDate.getYear() + "\n");
        buf.append("Gregorian Month: " + gregorianDate.getMonthValue() + "\n");
        buf.append("Gregorian Date: " + gregorianDate.getDayOfMonth() + "\n");
        buf.append("Is Leap Year: " + gregorianDate.isLeapYear() + "\n");
        buf.append("Day of Year: " + gregorianDate.getDayOfYear() + "\n");
        buf.append("Day of Week: " + gregorianDate.getDayOfWeek() + "\n");
        buf.append("Chinese Year: " + lunarDate.getYear() + "\n");
        buf.append("Chinese Month: " + lunarDate.getMonthValue() + "\n");
        buf.append("Chinese Date: " + lunarDate.getDayOfMonth() + "\n");
        buf.append("Heavenly Stem: " + ((lunarDate.getYear() - 1) % 10) + "\n");
        buf.append("Earthly Branch: " + ((lunarDate.getYear() - 1) % 12) + "\n");

        return buf.toString();
    }
}
