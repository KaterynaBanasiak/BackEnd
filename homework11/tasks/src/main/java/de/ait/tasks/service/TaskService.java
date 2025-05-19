package de.ait.tasks.service;

import de.ait.tasks.dto.TaskCreateDto;
import de.ait.tasks.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskCreateDto dto);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
}

