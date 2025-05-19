package de.ait.demo2.models;

public class Task {
    private Long id;
    private String description;
    private int priority;

    public Task() {}

    public Task(Long id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
