package org.slerpio.repository;

import org.slerpio.entity.TaskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAnswerRepository extends JpaRepository<TaskAnswer, Long> {
}