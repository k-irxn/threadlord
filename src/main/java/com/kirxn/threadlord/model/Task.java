package com.kirxn.threadlord.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.kirxn.threadlord.constants.TaskStatus;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
