package de.ait.tasks.service;

import de.ait.tasks.dto.ProgrammerCreateDto;
import de.ait.tasks.model.Programmer;
import de.ait.tasks.model.Task;
import de.ait.tasks.repository.ProgrammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammerServiceImpl implements ProgrammerService {

    private final ProgrammerRepository programmerRepository;

    @Override
    public List<Programmer> getAllProgrammers() {
        return programmerRepository.findAll();
    }

    @Override
    public Programmer getProgrammerById(Long id) {
        return programmerRepository.findById(id);
    }

    @Override
    public List<Task> getTasksByProgrammer(Long programmerId) {
        return programmerRepository.findTasksByProgrammerId(programmerId);
    }

    @Override
    public void addTaskToProgrammer(Long programmerId, Long taskId) {
        programmerRepository.addTaskToProgrammer(programmerId, taskId);
    }

    @Override
    public Programmer createProgrammer(ProgrammerCreateDto dto) {
        Programmer programmer = new Programmer(null, dto.getName());
        return programmerRepository.save(programmer);
    }
}
