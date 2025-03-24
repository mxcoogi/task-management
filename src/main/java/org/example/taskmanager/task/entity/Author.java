package org.example.taskmanager.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Author {

    private Long id;
    private String email;
    private String name;
    private LocalDate created_at;
    private LocalDate updated_at;
}
