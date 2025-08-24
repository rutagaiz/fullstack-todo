package org.example.fullstacktodo.DAO;

import org.example.fullstacktodo.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<Task, Long>{
    //List<Task> findByCompleted(boolean completed);
}
// spring boot jpa sumazina DAO code, nes suteikia metodus kaip findAll, findbyid, save, update, delete ir tt


