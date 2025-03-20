package org.example.taskmanager.task.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {
    private Long id;
    private String taskName;
    private String authorName;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
