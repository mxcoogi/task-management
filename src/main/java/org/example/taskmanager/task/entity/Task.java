package org.example.taskmanager.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Task {
    private Long id;
    private String taskName;
    private String authorEmail;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Task(Long id, String taskName, String email) {
        this.taskName = taskName;
        this.authorEmail = email;
        this.id = id;
    }

}
