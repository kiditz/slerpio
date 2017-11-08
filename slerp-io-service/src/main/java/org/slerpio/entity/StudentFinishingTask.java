package org.slerpio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "student_finishing_task")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class StudentFinishingTask {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_FINISHING_TASK_SEQ")
	@SequenceGenerator(name = "STUDENT_FINISHING_TASK_SEQ", sequenceName = "student_finishing_task_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "student_finish_task_id")
	private Long studentFinishTaskId;
	@Column(name = "flg_finish")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.StudentFinishingTask.flgFinish")
	@Size(min = 1, max = 1)
	private String flgFinish;
	
	@Column(name = "finish_at")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishAt;

	@ManyToOne
	@JoinColumn(name = "user_profile_id", referencedColumnName = "user_profile_id")
	private UserProfile userProfileId;
	@ManyToOne
	@JoinColumn(name = "task_id", referencedColumnName = "task_id")
	private Task taskId;

	@JsonProperty
	public Long getStudentFinishTaskId() {
		return studentFinishTaskId;
	}

	public void setStudentFinishTaskId(Long studentFinishTaskId) {
		this.studentFinishTaskId = studentFinishTaskId;
	}

	@JsonProperty
	public String getFlgFinish() {
		return flgFinish;
	}

	public void setFlgFinish(String flgFinish) {
		this.flgFinish = flgFinish;
	}

	@JsonProperty
	public Date getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(Date finishAt) {
		this.finishAt = finishAt;
	}

	@JsonProperty
	public UserProfile getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(UserProfile userProfileId) {
		this.userProfileId = userProfileId;
	}

	@JsonProperty
	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}
}