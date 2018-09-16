package workalendar.core.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Day {
    private LocalDate date;
    private String label;

    public Day(LocalDate date, String label) {
        this.date = date;
        this.label = label;
    }
}
