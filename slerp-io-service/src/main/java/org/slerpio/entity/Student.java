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
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.slerpio.entity.TaskUser;

@Entity
@Table(name = "student")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Student {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ")
	@SequenceGenerator(name = "STUDENT_SEQ", sequenceName = "student_seq", initialValue = 1, allocationSize = 1)
	private Long studentId;
	@Column(name = "firstname")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Student.firstname")
	@Size(min = 1, max = 60)
	private String firstname;
	@Column(name = "lastname")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Student.lastname")
	@Size(min = 1, max = 60)
	private String lastname;
	@Column(name = "username")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Student.username")
	@Size(min = 1, max = 60)
	private String username;
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column(name = "day_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dayOfBirth;
	@Column(name = "parent_name")
	@Size(min = 1, max = 60)
	private String parentName;
	@Column(name = "address")
	private String address;
	@Column(name = "postal_code")
	@Size(min = 1, max = 11)
	private String postalCode;
	@Column(name = "active")
	private Boolean active;
	@ManyToOne(optional = false)
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;
	@ManyToOne
	@JoinColumn(name = "class_id", referencedColumnName = "school_class_id")
	private SchoolClass classId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
	private List<TaskUser> taskUserList;

	@JsonProperty
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@JsonProperty
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@JsonProperty
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonProperty
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonProperty
	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	@JsonProperty
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@JsonProperty
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@JsonProperty
	public School getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}

	@JsonProperty
	public SchoolClass getClassId() {
		return classId;
	}

	public void setClassId(SchoolClass classId) {
		this.classId = classId;
	}

	public List<TaskUser> getTaskUserList() {
		return taskUserList;
	}

	public void setTaskUserList(List<TaskUser> taskUserList) {
		this.taskUserList = taskUserList;
	}
}