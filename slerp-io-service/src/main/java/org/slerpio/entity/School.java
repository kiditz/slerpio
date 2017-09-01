package org.slerpio.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "school")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class School {

	@Id
	@Column(name = "school_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHOOL_CLASS_SEQ")
	@SequenceGenerator(name = "SCHOOL_CLASS_SEQ", sequenceName = "school_class_seq", initialValue = 1, allocationSize = 1)
	private Long schoolId;
	@Column(name = "name")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.name")
	@Size(min = 1, max = 60)
	private String name;
	@Column(name = "description")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.description")
	private String description;
	@Column(name = "latitude")
	private BigDecimal latitude;
	@Column(name = "longitude")
	private BigDecimal longitude;
	@Column(name = "address")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.address")
	private String address;
	@Column(name = "postal_code")
	@Size(min = 1, max = 11)
	private String postalCode;
	@Column(name = "build_at")
	@Temporal(TemporalType.DATE)
	private Date buildAt;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
	private List<SchoolClass> schoolClassList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
	private List<Student> studentList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
	private List<Teacher> teacherList;

	@JsonProperty
	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
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

	@JsonProperty
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@JsonProperty
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
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
	public Date getBuildAt() {
		return buildAt;
	}

	public void setBuildAt(Date buildAt) {
		this.buildAt = buildAt;
	}

	public List<SchoolClass> getSchoolClassList() {
		return schoolClassList;
	}

	public void setSchoolClassList(List<SchoolClass> schoolClassList) {
		this.schoolClassList = schoolClassList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
}