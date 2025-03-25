package org.example.taskmanager.task.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
public class TaskRequestDto {

    private Long id;
    private String taskName;
    @Setter
    private String authorName;
    private String authorEmail;
    private String authorPassword;
    private LocalDate created_at;
    private LocalDate updated_at;

}
