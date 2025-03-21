package org.example.taskmanager.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Task {
    private Long id;
    private String taskName;
    private String authorName;
    private String password;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Task(String taskName, String authorName, String password){
        this.taskName = taskName;
        this.authorName = authorName;
        this.password = password;
    }

}
