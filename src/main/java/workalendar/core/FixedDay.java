package workalendar.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FixedDay {
    private int month;
    private int day;
    private String label;

    public Day toDay(int year) {
        return new Day(LocalDate.of(year, this.month, this.day), this.label);
    }
}
