package de.ait.tasks.dto;

import de.ait.tasks.model.Priority;
import lombok.Data;

@Data
public class TaskCreateDto {
    private String title;
    private String description;
    private Priority priority;
}
