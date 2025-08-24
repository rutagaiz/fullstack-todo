package org.example.fullstacktodo.DTO;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
    @NotBlank(message = "Task can't be empty")
    @Size(min=1, max=50)
    private String text;
    private boolean completed;

    public TaskDTO(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
