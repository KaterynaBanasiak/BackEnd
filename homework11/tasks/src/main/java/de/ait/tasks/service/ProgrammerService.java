package de.ait.tasks.service;

import de.ait.tasks.dto.ProgrammerCreateDto;
import de.ait.tasks.model.Programmer;
import de.ait.tasks.model.Task;

import java.util.List;

public interface ProgrammerService {
    List<Programmer> getAllProgrammers();
    Programmer getProgrammerById(Long id);
    List<Task> getTasksByProgrammer(Long programmerId);
    void addTaskToProgrammer(Long programmerId, Long taskId);
    Programmer createProgrammer(ProgrammerCreateDto dto);
}
