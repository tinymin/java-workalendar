package workalendar.core.model;

import java.time.LocalDate;
import java.util.Comparator;

public class FixedDayComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {

        // FIXME: 2018-05-14 Compare statement is not collect. Must be modify.
        int date1 = ((FixedDay)o1).getMonth();
        int date2 = ((FixedDay)o2).getMonth();

        if (date1 < date2)
            return 1;
        else if(date1 == date2)
            return 0;

        return -1;
    }
}
