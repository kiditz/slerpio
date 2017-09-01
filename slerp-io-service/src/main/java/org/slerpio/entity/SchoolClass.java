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
import javax.validation.constraints.Size;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.slerpio.entity.Student;
import org.slerpio.entity.Task;

@Entity
@Table(name = "school_class")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class SchoolClass {

	@Id
	@Column(name = "school_class_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHOOL_CLASS_SEQ")
	@SequenceGenerator(name = "SCHOOL_CLASS_SEQ", sequenceName = "school_class_seq", initialValue = 1, allocationSize = 1)
	private Long schoolClassId;
	@Column(name = "name")
	@Size(min = 1, max = 60)
	private String name;
	@Column(name = "description")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.description")
	private String description;
	@ManyToOne(optional = false)
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
	private List<Student> studentList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolClassId")
	private List<Task> taskList;

	@JsonProperty
	public Long getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Long schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//@JsonProperty
	public School getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
}