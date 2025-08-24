package org.example.fullstacktodo.Controller;

import jakarta.validation.Valid;
import org.example.fullstacktodo.DAO.ToDoRepository;
import org.example.fullstacktodo.DTO.TaskDTO;
import org.example.fullstacktodo.Model.Task;
import org.example.fullstacktodo.Service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class AppController {

    private TaskService taskService;

    public AppController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody TaskDTO dto) {
        return taskService.createTask(dto);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
