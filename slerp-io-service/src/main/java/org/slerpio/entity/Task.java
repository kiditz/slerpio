package org.slerpio.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.slerpio.entity.TaskQuestion;
import org.slerpio.entity.StudentFinishingTask;

@Entity
@Table(name = "task")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Task {

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ANSWER_SEQ")
	@SequenceGenerator(name = "TASK_ANSWER_SEQ", sequenceName = "task_answer_seq", initialValue = 1, allocationSize = 1)
	private Long taskId;
	@Column(name = "title")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Task.title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Task.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "update_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Task.updateAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;
	@ManyToOne
	@JoinColumn(name = "school_class_id", referencedColumnName = "school_class_id")
	private SchoolClass schoolClassId;
	@ManyToOne
	@JoinColumn(name = "user_profile_id", referencedColumnName = "user_profile_id")
	private UserProfile userProfileId;
	@ManyToOne
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
	private List<TaskQuestion> taskQuestionList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
	private List<StudentFinishingTask> studentFinishingTaskList;

	@JsonProperty
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty
	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	// @JsonProperty
	public SchoolClass getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(SchoolClass schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	@JsonProperty
	public UserProfile getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(UserProfile userProfileId) {
		this.userProfileId = userProfileId;
	}

	// @JsonProperty
	public School getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}
	// @JsonProperty
	public List<TaskQuestion> getTaskQuestionList() {
		return taskQuestionList;
	}

	public void setTaskQuestionList(List<TaskQuestion> taskQuestionList) {
		this.taskQuestionList = taskQuestionList;
	}

	public List<StudentFinishingTask> getStudentFinishingTaskList() {
		return studentFinishingTaskList;
	}

	public void setStudentFinishingTaskList(
			List<StudentFinishingTask> studentFinishingTaskList) {
		this.studentFinishingTaskList = studentFinishingTaskList;
	}
}