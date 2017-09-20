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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.slerpio.entity.Profile;

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
	@Column(name = "address")
	private String address;
	@Column(name = "description")
	private String description;
	@Column(name = "latitude")
	private BigDecimal latitude;
	@Column(name = "longitude")
	private BigDecimal longitude;
	@Column(name = "build_at")
	@Temporal(TemporalType.DATE)
	private Date buildAt;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "last_update")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
	private List<Profile> profileList;

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
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	public Date getBuildAt() {
		return buildAt;
	}

	public void setBuildAt(Date buildAt) {
		this.buildAt = buildAt;
	}

	@JsonProperty
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}
}