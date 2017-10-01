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
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "contact")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Contact {

	@Id
	@Column(name = "contact_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
	@SequenceGenerator(name = "CONTACT_SEQ", sequenceName = "contact_seq", initialValue = 1, allocationSize = 1)
	private Long contactId;
	@Column(name = "username")
	@Size(min = 1, max = 60)
	private String username;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Contact.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "last_update")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Contact.lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@ManyToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
	private Profile profileId;

	@JsonProperty
	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@JsonProperty
	public Profile getProfileId() {
		return profileId;
	}

	public void setProfileId(Profile profileId) {
		this.profileId = profileId;
	}
}