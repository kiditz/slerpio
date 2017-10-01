package org.slerpio.repository;

import java.util.Date;

import org.slerpio.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Query("SELECT a FROM Activity a WHERE a.schoolId.schoolId = :schoolId")
	public Page<Activity> findActivityBySchoolId(@Param("schoolId") long schoolId, Pageable pageable);

	@Query("SELECT a FROM Activity a WHERE a.activityId = :activityId")
	public Activity findActivityByActivityId(@Param("activityId") long activityId);

	@Query("SELECT a FROM Activity a WHERE a.schoolId.schoolId = :schoolId AND a.lastUpdate BETWEEN :startDate AND :endDate")
	public Page<Activity> getActivityBetweenDate(@Param("schoolId") Long schoolId, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate, Pageable pageable);
}