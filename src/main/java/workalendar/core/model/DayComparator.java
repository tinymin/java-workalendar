package workalendar.core.model;

import java.time.LocalDate;
import java.util.Comparator;

public class DayComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        LocalDate date1 = ((Day)o1).getDate();
        LocalDate date2 = ((Day)o2).getDate();

        if (date1.isBefore(date2))
            return -1;
        else if(date1.isEqual(date2))
            return 0;

        return 1;
    }
}
