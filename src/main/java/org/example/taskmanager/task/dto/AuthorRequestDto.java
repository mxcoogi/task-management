package org.example.taskmanager.task.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorRequestDto {
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
