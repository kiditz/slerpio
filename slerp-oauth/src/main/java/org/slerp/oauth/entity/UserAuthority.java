package org.slerp.oauth.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * The entity for user_authority
 * 
 * @author kiditz
 */
@Entity
@Table(name = "user_authority")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class UserAuthority {

	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_AUTHORITY_SEQ")
	@SequenceGenerator(name = "USER_AUTHORITY_SEQ", sequenceName = "user_authority_seq", initialValue = 1, allocationSize = 1)
	private Long authorityId;
	@Column(name = "authority")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserAuthority.authority")
	@Size(min = 1, max = 60)
	private String authority;
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserPrincipal userId;

	public UserAuthority() {
	}

	public UserAuthority(Long authorityId, String authority, UserPrincipal userId) {
		this.authorityId = authorityId;
		this.authority = authority;
		this.userId = userId;
	}

	@JsonProperty
	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	@JsonProperty
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	//@JsonProperty
	public UserPrincipal getUserId() {
		return userId;
	}

	public void setUserId(UserPrincipal userId) {
		this.userId = userId;
	}
}