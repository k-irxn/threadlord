package com.kirxn.threadlord.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.kirxn.threadlord.constants.TaskStatus;
import com.kirxn.threadlord.model.Task;
import com.kirxn.threadlord.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WorkerPool {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final TaskManager taskManager;
    private final TaskRepository taskRepository;

    public WorkerPool(TaskManager taskManager, TaskRepository taskRepository) {
        this.taskManager = taskManager;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void startProcessing() {
        // Submit tasks from the queue to virtual threads
        executor.submit(() -> {
            while (true) {
                try {
                    Task task = taskManager.getTaskQueue().take();
                    processTask(task);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private void processTask(Task task) {
        try {
            task.setTaskStatus(TaskStatus.RUNNING);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(task);

            // Simulate task execution (replace with actual task logic)
            Thread.sleep(5000);

            task.setTaskStatus(TaskStatus.COMPLETED);
        } catch (Exception e) {
            task.setTaskStatus(TaskStatus.FAILED);
        } finally {
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(task);
        }
    }
}
