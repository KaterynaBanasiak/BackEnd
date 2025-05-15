package de.ait.tasks.service;

import de.ait.tasks.dto.TaskCreateDto;
import de.ait.tasks.model.Task;
import de.ait.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(TaskCreateDto dto) {
        Task task = new Task(null, dto.getTitle(), dto.getDescription(), dto.getPriority());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
