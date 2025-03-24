package org.example.taskmanager.task.dto;


import lombok.Getter;

import java.time.LocalDate;


@Getter
public class TaskRequestDto {

    private String taskName;
    private String authorName;
    private String authorEmail;
    private String password;
    private LocalDate created_at;
    private LocalDate updated_at;
}
