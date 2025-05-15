package de.ait.demo2.controllers;

import de.ait.demo2.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private Map<Long, Task> tasks = new HashMap<>();
    private long currentId = 1;

    // Получить список всех задач
    @GetMapping
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Получить задачу по id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    // Создать новую задачу
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(currentId++);
        tasks.put(task.getId(), task);
        return task;
    }

    // Удалить задачу по id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        tasks.remove(id);
        return ResponseEntity.ok().build();
    }
}
