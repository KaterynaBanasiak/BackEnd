package de.ait.tasks.repository;

import de.ait.tasks.model.Programmer;
import de.ait.tasks.model.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProgrammerRepositoryMapImpl implements ProgrammerRepository {

    private final TaskRepository taskRepository;
    private static final Map<Long, Programmer> programmers = new HashMap<>();
    private static Long lastId = 3L;

    public ProgrammerRepositoryMapImpl(@Qualifier("taskRepositoryMapImpl") TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

        programmers.put(1L, new Programmer(1L, "Jack"));
        programmers.put(2L, new Programmer(2L, "Nick"));
        programmers.put(3L, new Programmer(3L, "John"));

        addTaskToProgrammer(1L, 1L);
        addTaskToProgrammer(1L, 2L);
        addTaskToProgrammer(2L, 3L);
    }

    @Override
    public List<Programmer> findAll() {
        return new ArrayList<>(programmers.values());
    }

    @Override
    public Programmer findById(Long id) {
        Programmer programmer = programmers.get(id);
        if (programmer == null) {
            throw new RuntimeException("Programmer not found");
        }
        return programmer;
    }

    @Override
    public Programmer save(Programmer programmer) {
        programmer.setId(++lastId);
        programmers.put(lastId, programmer);
        return programmer;
    }

    @Override
    public void addTaskToProgrammer(Long programmerId, Long taskId) {
        Programmer programmer = findById(programmerId);
        Task task = taskRepository.findById(taskId);
        programmer.addTask(task);
    }

    @Override
    public void deleteTaskToProgrammer(Long programmerId, Long taskId) {
        Programmer programmer = findById(programmerId);
        Task task = taskRepository.findById(taskId);
        programmer.removeTask(task);
    }

    @Override
    public List<Task> findTasksByProgrammerId(Long id) {
        return new ArrayList<>(findById(id).getTasks());
    }
}

