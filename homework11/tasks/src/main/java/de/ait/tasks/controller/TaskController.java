package de.ait.tasks.controller;

import de.ait.tasks.dto.TaskCreateDto;
import de.ait.tasks.model.Task;
import de.ait.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public Task createTask(@RequestBody TaskCreateDto dto) {
        return taskService.createTask(dto);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
}
