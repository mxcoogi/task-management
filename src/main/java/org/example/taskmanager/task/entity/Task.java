package org.example.taskmanager.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Task {
    private Long id;
    private String taskName;
    private String authorName;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
