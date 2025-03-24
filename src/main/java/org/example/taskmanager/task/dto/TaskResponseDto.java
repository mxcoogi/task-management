package org.example.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class TaskResponseDto {
    private Long id;
    private String taskName;
    private Long authorId;
    private LocalDate created_at;
    private LocalDate updated_at;

    public TaskResponseDto(Long id, String taskName, Long authorId, LocalDate created_at, LocalDate updated_at){
        this.id = id;
        this.taskName = taskName;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.authorId = authorId;
    }
}
