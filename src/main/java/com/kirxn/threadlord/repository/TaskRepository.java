package com.kirxn.threadlord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirxn.threadlord.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

