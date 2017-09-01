package org.slerpio.repository;

import org.slerpio.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {
}