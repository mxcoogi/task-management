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
    private Long authorId;
    private String password;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Task(String taskName, String password, Long authorId){
        this.taskName = taskName;
        this.authorId = authorId;
        this.password = password;
    }

}
