package com.example.association.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    private Integer age;
    private String genre;
    @NotNull
    @NotEmpty
    private String name;
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }
    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
