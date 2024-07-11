//package jdbc.repository.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "user_role")
//public class User_RoleEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long Id;
//	@ManyToOne
//	@JoinColumn(name = "userid")
//	private UserEntity user;
//	@ManyToOne
//	@JoinColumn (name = "roleid")
//	private RoleEntity role;
//	public Long getId() {
//		return Id;
//	}
//	public void setId(Long id) {
//		Id = id;
//	}
//	public UserEntity getUser() {
//		return user;
//	}
//	public void setUser(UserEntity user) {
//		this.user = user;
//	}
//	public RoleEntity getRole() {
//		return role;
//	}
//	public void setRole(RoleEntity role) {
//		this.role = role;
//	}
//	
//}
