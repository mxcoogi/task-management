package org.example.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestDto {

    private String taskName;
    private String authorName;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
