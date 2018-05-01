package workalendar.core;

import org.junit.Test;
import workalendar.core.model.Day;

import java.util.SortedSet;

public class WorkalendarTest {
    Workalendar workalendar = new Workalendar() {
        @Override
        public SortedSet<Day> getVariableDays(int year) {
            return null;
        }
    };

    @Test
    public void holidays() {
        workalendar.holidays(2013);
    }
}