package com.belhopat.backoffice.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column ( unique = true )
	private String username;

	private String password;

	private String email;
	
	private Byte[] salt;

	@Column( name = "forgot_password_status" )
	private Boolean forgotPasswordStatus;
	
	@Column( name = "forgot_password_url" )
	private String forgotPasswordToken;
	
	@OneToOne(fetch = FetchType.EAGER)
	private MasterRoles primaryRole;
	
	
	public MasterRoles getPrimaryRole() {
		return primaryRole;
	}

	public void setPrimaryRole(MasterRoles primaryRole) {
		this.primaryRole = primaryRole;
	}

	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
    @JoinTable(name="User_Roles", 
                joinColumns={@JoinColumn(name="user_id")}, 
                inverseJoinColumns={@JoinColumn(name="role_id")})
    private Set<MasterRoles> roles = new HashSet<MasterRoles>();
	
	
	public Set<MasterRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<MasterRoles> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	private LookupDetail designation;

	

	public LookupDetail getDesignation() {
		return designation;
	}

	public void setDesignation(LookupDetail designation) {
		this.designation = designation;
	}

	
	public User() {
	}
	
	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Byte[] getSalt() {
		return salt;
	}
	
	public void setSalt(Byte[] salt) {
		this.salt = salt;
	}
	
	public Boolean getForgotPasswordStatus() {
		return forgotPasswordStatus;
	}
	
	public void setForgotPasswordStatus(Boolean forgotPasswordStatus) {
		this.forgotPasswordStatus = forgotPasswordStatus;
	}

	public String getForgotPasswordToken() {
		return forgotPasswordToken;
	}

	public void setForgotPasswordToken(String forgotPasswordToken) {
		this.forgotPasswordToken = forgotPasswordToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", salt="
				+ Arrays.toString(salt) + ", forgotPasswordStatus=" + forgotPasswordStatus + ", forgotPasswordToken="
				+ forgotPasswordToken + ", primaryRole=" + primaryRole + ", roles=" + roles + ", designation="
				+ designation + "]";
	}
}
