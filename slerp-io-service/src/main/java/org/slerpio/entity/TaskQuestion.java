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
import org.slerpio.entity.TaskAnswer;

@Entity
@Table(name = "task_question")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class TaskQuestion {

	@Id
	@Column(name = "task_question_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_QUESTION_SEQ")
	@SequenceGenerator(name = "TASK_QUESTION_SEQ", sequenceName = "task_question_seq", initialValue = 1, allocationSize = 1)
	private Long taskQuestionId;
	@Column(name = "question")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskQuestion.question")
	private String question;
	@Column(name = "answered_key")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskQuestion.answeredKey")
	private String answeredKey;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskQuestion.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "update_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskQuestion.updateAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@ManyToOne
	@JoinColumn(name = "task_id", referencedColumnName = "task_id")
	private Task taskId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskQuestionId")
	private List<TaskAnswer> taskAnswerList;

	@JsonProperty
	public Long getTaskQuestionId() {
		return taskQuestionId;
	}

	public void setTaskQuestionId(Long taskQuestionId) {
		this.taskQuestionId = taskQuestionId;
	}

	@JsonProperty
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@JsonProperty
	public String getAnsweredKey() {
		return answeredKey;
	}

	public void setAnsweredKey(String answeredKey) {
		this.answeredKey = answeredKey;
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

//	@JsonProperty
	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}
	@JsonProperty
	public List<TaskAnswer> getTaskAnswerList() {
		return taskAnswerList;
	}

	public void setTaskAnswerList(List<TaskAnswer> taskAnswerList) {
		this.taskAnswerList = taskAnswerList;
	}
}