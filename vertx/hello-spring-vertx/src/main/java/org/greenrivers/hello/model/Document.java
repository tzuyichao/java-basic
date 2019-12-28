package org.greenrivers.hello.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Document {
    private Integer id;
    private String name;

    public Document(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
