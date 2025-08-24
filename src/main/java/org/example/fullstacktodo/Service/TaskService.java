package org.example.fullstacktodo.Service;

import org.example.fullstacktodo.DAO.ToDoRepository;
import org.example.fullstacktodo.DTO.TaskDTO;
import org.example.fullstacktodo.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private ToDoRepository repository;

    @Autowired
    public TaskService(ToDoRepository repository){
        this.repository = repository;
    }

    public Task createTask(TaskDTO dto) {
        Task task = new Task();
        task.setText(dto.getText());
        task.setCompleted(dto.isCompleted());
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public Task updateTask(Long id, TaskDTO dto) {
        Optional<Task> existingTask = repository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setText(dto.getText());
            task.setCompleted(dto.isCompleted());
            return repository.save(task);
        } else {
            throw new RuntimeException("Task not found with id " + id);
        }
    }

    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public List<Task> getTasks() {
        return repository.findAll();
    }

}
