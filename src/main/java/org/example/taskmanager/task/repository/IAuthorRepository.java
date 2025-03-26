package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.entity.Author;

public interface IAuthorRepository {

    Author saveAuthor(String email, String password, String name);

    Author getAuthor(String email);

    int updateAuthorName(String email, String name);

    Author vertifyAuthorByEmailPassword(String email, String password);

}
