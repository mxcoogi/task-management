package org.example.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto {
    private Long id;
    private String taskName;
    private String authorName;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public TaskResponseDto(Long id, String taskName, String authorName, LocalDateTime created_at, LocalDateTime updated_at){
        this.id = id;
        this.authorName = authorName;
        this.taskName = taskName;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
