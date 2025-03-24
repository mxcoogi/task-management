package org.example.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AuthorResponseDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate created_at;
    private LocalDate updated_at;
}
