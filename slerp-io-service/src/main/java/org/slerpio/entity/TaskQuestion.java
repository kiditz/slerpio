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
	@Column(name = "text")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskQuestion.text")
	private String text;
	@Column(name = "image")
	private String image;
	@Column(name = "answered_key")
	private Long answeredKey;

	@ManyToOne(optional = false)
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
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@JsonProperty
	public Long getAnsweredKey() {
		return answeredKey;
	}

	public void setAnsweredKey(Long answeredKey) {
		this.answeredKey = answeredKey;
	}

	@JsonProperty
	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}

	public List<TaskAnswer> getTaskAnswerList() {
		return taskAnswerList;
	}

	public void setTaskAnswerList(List<TaskAnswer> taskAnswerList) {
		this.taskAnswerList = taskAnswerList;
	}
}