package de.ait.tasks.repository;

import de.ait.tasks.model.Task;

import java.util.List;

public interface TaskRepository {
    Task findById(Long id);
    List<Task> findAll();
    Task save(Task task);
}

