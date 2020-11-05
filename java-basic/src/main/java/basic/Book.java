package basic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Book {
    private String name;
    private BigDecimal price;
}
