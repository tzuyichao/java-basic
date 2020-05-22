package core.observable;

import lombok.Data;

@Data
public class Person {
    private String name;
    private Integer score;

    public Person(String name, Integer score) {
        this.name = name;
        this.score = score;
    }
}
