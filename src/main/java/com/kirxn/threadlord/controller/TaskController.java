package com.kirxn.threadlord.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kirxn.threadlord.dto.TaskRequest;
import com.kirxn.threadlord.model.Task;
import com.kirxn.threadlord.service.TaskManager;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskManager taskManager;

    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @PostMapping("/submit")
    public ResponseEntity<Task> submitTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskManager.submitTask(taskRequest.getDescription());
        return ResponseEntity.ok(task);
    }

}
