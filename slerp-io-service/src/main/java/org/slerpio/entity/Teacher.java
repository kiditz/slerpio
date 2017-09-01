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

@Entity
@Table(name = "teacher")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Teacher {

	@Id
	@Column(name = "teacher_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHER_SEQ")
	@SequenceGenerator(name = "TEACHER_SEQ", sequenceName = "teacher_seq", initialValue = 1, allocationSize = 1)
	private Long teacherId;
	@Column(name = "firstname")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Teacher.firstname")
	@Size(min = 1, max = 60)
	private String firstname;
	@Column(name = "lastname")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Teacher.lastname")
	@Size(min = 1, max = 60)
	private String lastname;
	@Column(name = "username")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Teacher.username")
	@Size(min = 1, max = 60)
	private String username;
	@Column(name = "day_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dayOfBirth;
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

	@JsonProperty
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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
	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
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
}