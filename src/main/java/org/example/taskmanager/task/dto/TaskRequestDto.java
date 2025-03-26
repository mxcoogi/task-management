package org.example.taskmanager.task.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class TaskRequestDto {

    @NotBlank
    @Max(200)
    private String taskName;
    @Email
    @NotBlank
    private String authorEmail;
    @NotBlank
    private String authorPassword;

}
