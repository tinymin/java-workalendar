package workalendar.core.model;

import java.time.LocalDate;
import java.util.Comparator;

public class FixedDayComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {

        FixedDay day1 = (FixedDay)o1;
        FixedDay day2 = (FixedDay)o2;

        LocalDate date1 = LocalDate.of(1900, day1.getMonth(), day1.getDay());
        LocalDate date2 = LocalDate.of(1900, day2.getMonth(), day2.getDay());

        if (date1.isBefore(date2))
            return 1;
        else if(date1.isEqual(date2))
            return 0;

        return -1;
    }
}
