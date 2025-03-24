package org.example.taskmanager.task.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorRequestDto {
    private Long id;
    private String email;
    private String name;
}
