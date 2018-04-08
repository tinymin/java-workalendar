package net.suncheol.kim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class Calendar {
    private List getFixedHolidays(int year) {
        return new ArrayList<>();
    }

    private List getVariableDays(int year) {
        return new ArrayList<>();
    }

    public List getCalendarHolidays(int year) {
        return Collections.singletonList(Stream.concat(getFixedHolidays(year).stream(), getVariableDays(year).stream())
                .collect(toList()));
    }

    public List holidays() {
        return holidays(LocalDate.now().getYear());
    }

    private List holidays(int year) {
        return null;
    }
}
