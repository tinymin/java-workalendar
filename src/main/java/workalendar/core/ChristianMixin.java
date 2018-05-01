package workalendar.core;

import lombok.Setter;
import workalendar.util.Easter;
import workalendar.util.Easter.EASTER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

@Setter
public class ChristianMixin extends Workalendar {
    private EASTER easterMethod;  // to be assigned in the inherited mixin;
    private boolean includeEpiphany;
    private boolean includeCleanMonday;
    private boolean includeAnnunciation;
    private boolean includeAshWednesday;
    private String ashWednesdayLabel;
    private boolean includePalmSunday;
    private boolean includeHolyThursday;
    private boolean includeGoodFriday;
    private String goodFridayLabel;
    private boolean includeEasterMonday;
    private boolean includeEasterSaturday;
    private boolean includeEasterSunday;
    private boolean includeAllSaints;
    private boolean includeImmaculateConception;
    private boolean includeChristmas;
    private boolean includeChristmasEve;
    private boolean includeAscension;
    private boolean includeAssumption;
    private boolean includeWhitSunday;
    private String whitSundayLabel;
    private boolean includeWhitMonday;
    private String whitMondayLabel;
    private boolean includeCorpusChristi;
    private boolean includeBoxingDay;
    private String boxingDayLabel;

    ChristianMixin() {
        easterMethod = null;
        includeEpiphany = false;
        includeCleanMonday = false;
        includeAnnunciation = false;
        includeAshWednesday = false;
        ashWednesdayLabel = "Ash Wednesday";
        includePalmSunday = false;
        includeHolyThursday = false;
        includeGoodFriday = false;
        goodFridayLabel = "Good Friday";
        includeEasterMonday = false;
        includeEasterSaturday = false;
        includeEasterSunday = false;
        includeAllSaints = false;
        includeImmaculateConception = false;
        includeChristmas = true;
        includeChristmasEve = false;
        includeAscension = false;
        includeAssumption = false;
        includeWhitSunday = false;
        whitSundayLabel = "Whit Sunday";
        includeWhitMonday = false;
        whitMondayLabel = "Whit Monday";
        includeCorpusChristi = false;
        includeBoxingDay = false;
        boxingDayLabel = "Boxing Day";
    }


    public LocalDate getAshWednesday(int year) {
        return this.getEasterSunday(year).minusDays(46);
    }


    public LocalDate getPalmSunday(int year) {
        return this.getEasterSunday(year).minusDays(7);
    }


    /**
     * Return the date of the last thursday before easter
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getHolyThursday(int year) {
        return this.getEasterSunday(year).minusDays(3);
    }


    /**
     * Return the date of the last friday before easter
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getGoodFriday(int year) {
        return this.getEasterSunday(year).minusDays(2);
    }


    /**
     * Return the clean monday date
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getCleanMonday(int year) {
        return this.getEasterSunday(year).minusDays(48);
    }


    /**
     * Return the Easter Saturday date
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterSaturday(int year) {
        return this.getEasterSunday(year).minusDays(1);
    }


    /**
     * Return the date of the easter (sunday) -- following the easter method
     * Easter Sunday = 부활절 일요일
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterSunday(int year) {
        return Easter.easter(year, this.easterMethod);
    }


    /**
     * Return the date of the monday after easter
     * Easter Monday = 부활절 다음 날 월요일
     *
     * @param year
     * @return LocalDate
     */
    public LocalDate getEasterMonday(int year) {
        return this.getEasterSunday(year).plusDays(1);
    }


    public LocalDate getAscensionThursday(int year) {
        return this.getEasterSunday(year).plusDays(39);
    }


    public LocalDate getWhitMonday(int year) {
        return this.getEasterSunday(year).plusDays(50);
    }


    public LocalDate getWhitSunday(int year) {
        return this.getEasterSunday(year).plusDays(49);
    }


    public LocalDate getCorpusChristi(int year) {
        return this.getEasterSunday(year).plusDays(60);
    }


    /**
     * When Christmas and/or Boxing Day falls on a weekend, it is rolled
     * forward to the next weekday.
     *
     * @param year
     * @return List<LocalDate>
     */
    public List<LocalDate> shiftChristmasBoxingDays(int year) {
        LocalDate christmas = LocalDate.of(year, 12, 25);
        LocalDate boxingDay = LocalDate.of(year, 12, 26);
        String boxingDayLabel = String.format("%s Shift", this.boxingDayLabel);

        List results = new ArrayList<>();

        if ( this.getWeekendDays().contains(christmas.getDayOfWeek()) ) {
            LocalDate shift = this.findFollowingWorkingDay(christmas);
            results.add(new Day(shift, "Christmas Shift"));
            results.add(new Day(shift.plusDays(1), boxingDayLabel));
        }
        else if ( this.getWeekendDays().contains(boxingDay.getDayOfWeek()) ) {
            LocalDate shift = this.findFollowingWorkingDay(boxingDay);
            results.add(new Day(shift, boxingDayLabel));
        }
        return results;
    }


    /**
     * Return the christian holidays list according to the mixin
     *
     * @param year
     * @return SortedSet<Day>
     */
    @Override
    public SortedSet<Day> getVariableDays(int year) {
        SortedSet<Day> days = this.getVariableDays(year);

        if (this.includeEpiphany) {
            days.add(new Day(LocalDate.of(year, 1, 6), "Epiphany"));
        }
        if (this.includeCleanMonday) {
            days.add(new Day(this.getCleanMonday(year), "Clean Monday"));
        }
        if (this.includeAnnunciation) {
            days.add(new Day(LocalDate.of(year, 3, 25), "Annunciation"));
        }
        if (this.includeAshWednesday) {
            days.add(new Day(this.getAshWednesday(year), this.ashWednesdayLabel));
        }
        if (this.includePalmSunday) {
            days.add(new Day(this.getPalmSunday(year), "Palm Sunday"));
        }
        if (this.includeHolyThursday) {
            days.add(new Day(this.getHolyThursday(year), "Holy Thursday"));
        }
        if (this.includeGoodFriday) {
            days.add(new Day(this.getGoodFriday(year), this.goodFridayLabel));
        }
        if (this.includeEasterSaturday) {
            days.add(new Day(this.getEasterSaturday(year), "Easter Saturday"));
        }
        if (this.includeEasterSunday) {
            days.add(new Day(this.getEasterSunday(year), "Easter Sunday"));
        }
        if (this.includeEasterMonday) {
            days.add(new Day(this.getEasterMonday(year), "Easter Monday"));
        }
        if (this.includeAssumption) {
            days.add(new Day(LocalDate.of(year, 8, 15), "Assumption of Mary to Heaven"));
        }
        if (this.includeAllSaints) {
            days.add(new Day(LocalDate.of(year, 11, 1), "All Saints Day"));
        }
        if (this.includeImmaculateConception) {
            days.add(new Day(LocalDate.of(year, 12, 8), "Immaculate Conception"));
        }
        if (this.includeChristmas) {
            days.add(new Day(LocalDate.of(year, 12, 25), "Christmas Day"));
        }
        if (this.includeChristmasEve) {
            days.add(new Day(LocalDate.of(year, 12, 24), "Christmas Eve"));
        }
        if (this.includeBoxingDay) {
            days.add(new Day(LocalDate.of(year, 12, 26), this.boxingDayLabel));
        }
        if (this.includeAscension) {
            days.add(new Day(this.getAscensionThursday(year), "Ascension Thursday"));
        }
        if (this.includeWhitMonday) {
            days.add(new Day(this.getWhitMonday(year), this.whitMondayLabel));
        }
        if (this.includeWhitSunday) {
            days.add(new Day(this.getWhitSunday(year), this.whitSundayLabel));
        }
        if (this.includeCorpusChristi) {
            days.add(new Day(this.getCorpusChristi(year), "Corpus Christi"));
        }

        return days;
    }
}
