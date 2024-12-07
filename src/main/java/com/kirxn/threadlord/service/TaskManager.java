package com.kirxn.threadlord.service;

import org.springframework.stereotype.Service;

import com.kirxn.threadlord.constants.TaskStatus;
import com.kirxn.threadlord.model.Task;
import com.kirxn.threadlord.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TaskManager {

    private final BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private final TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task submitTask(String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setTaskStatus(TaskStatus.QUEUED);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
        taskQueue.offer(task);
        return task;
    }

    public BlockingQueue<Task> getTaskQueue() {
        return taskQueue;
    }
}
