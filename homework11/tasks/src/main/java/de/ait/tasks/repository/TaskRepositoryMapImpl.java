package de.ait.tasks.repository;

import de.ait.tasks.model.Priority;
import de.ait.tasks.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepositoryMapImpl implements TaskRepository {

    private static final Map<Long, Task> tasks = new HashMap<>();
    private static Long lastId = 3L;

    static {
        tasks.put(1L, new Task(1L, "Bug fix", "Fix login issue", Priority.HIGH));
        tasks.put(2L, new Task(2L, "Write tests", "Cover service layer with tests", Priority.MEDIUM));
        tasks.put(3L, new Task(3L, "Deploy", "Deploy to staging", Priority.LOW));
    }

    @Override
    public Task findById(Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new RuntimeException("Task not found");
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task save(Task task) {
        task.setId(++lastId);
        tasks.put(lastId, task);
        return task;
    }
}
