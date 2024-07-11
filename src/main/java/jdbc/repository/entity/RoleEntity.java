package jdbc.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String code;
//	thu cong
//	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//	private List<User_RoleEntity> user_RoleEntities = new ArrayList<User_RoleEntity>();
	//
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<UserEntity> users = new ArrayList<UserEntity>();
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
//	public List<User_RoleEntity> getUser_RoleEntities() {
//		return user_RoleEntities;
//	}
//	public void setUser_RoleEntities(List<User_RoleEntity> user_RoleEntities) {
//		this.user_RoleEntities = user_RoleEntities;
//	}
	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
