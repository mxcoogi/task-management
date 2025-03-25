package org.example.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String taskName;
    private String authorName;
    private String authorEmail;
    private LocalDate created_at;
    private LocalDate updated_at;
}
