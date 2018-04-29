package workalendar.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Day {
    private LocalDate localDate;
    private String label;
}
