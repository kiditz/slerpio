package org.slerpio.repository;

import org.slerpio.entity.TaskQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskQuestionRepository
		extends
			JpaRepository<TaskQuestion, Long> {
}