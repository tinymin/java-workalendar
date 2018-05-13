package workalendar.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Day {
    private LocalDate date;
    private String label;
}
