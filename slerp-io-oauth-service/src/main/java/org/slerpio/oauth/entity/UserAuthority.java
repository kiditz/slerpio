package org.slerpio.oauth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user_authority")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class UserAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_AUTHORITY_SEQ")
	@SequenceGenerator(name = "USER_AUTHORITY_SEQ", sequenceName = "user_authority_seq", initialValue = 1, allocationSize = 1)
	private Long authorityId;
	@Column(name = "authority")
	private String authority;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserPrincipal userId;
	
	public UserAuthority() {	
	}
	public UserAuthority(String authority, UserPrincipal userId) {
		super();		
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
	
	public UserPrincipal getUserId() {
		return userId;
	}

	public void setUserId(UserPrincipal userId) {
		this.userId = userId;
	}
}