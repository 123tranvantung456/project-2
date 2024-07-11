package jdbc.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", nullable =  false, unique = true)
	private String userName;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "fullname")
	private String fullName;
	@Column(name = "status", nullable = false)
	private Integer status;
	@Column(name = "email")
	private String email;
	public Long getId() { 
		return id;
	}
	
	// thu cong
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private List<User_RoleEntity> user_RoleEntities = new ArrayList<User_RoleEntity>();
//	public List<User_RoleEntity> getUser_RoleEntities() {
//		return user_RoleEntities;
//	}
	//
	@ManyToMany
	@JoinTable (name = "user_role",
				joinColumns = @JoinColumn (name = "userid", nullable = false),
				inverseJoinColumns = @JoinColumn(name = "roleid", nullable = false)
			)
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	//	public void setUser_RoleEntities(List<User_RoleEntity> user_RoleEntities) {
//		this.user_RoleEntities = user_RoleEntities;
//	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
