package org.example.taskmanager.task.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
public class TaskRequestDto {

    @NotBlank
    private String taskName;
    @Email
    @NotBlank
    private String authorEmail;
    @NotBlank
    private String authorPassword;

}
