package org.slerpio.repository;

import org.slerpio.entity.StudentFinishingTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFinishingTaskRepository
		extends
			JpaRepository<StudentFinishingTask, Long> {
}