package workalendar.core.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FixedDay {
    private int month;
    private int day;
    private String label;

    public FixedDay(int month, int day, String label) {
        this. month = month;
        this.day = day;
        this.label = label;
    }

    public Day toDay(int year) {
        return new Day(LocalDate.of(year, this.month, this.day), this.label);
    }
}
