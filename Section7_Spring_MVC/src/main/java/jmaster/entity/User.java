package jmaster.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	private int id;
	private String username;
	private String password;
	private String role;
	private String img;

	@Id
	@Column(name = "id", nullable = false)
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "username", nullable = true, length = 45)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "password", nullable = true, length = 45)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "role", nullable = true, length = 45)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Basic
	@Column(name = "img", nullable = true, length = 45)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (id != user.id)
			return false;
		if (username != null ? !username.equals(user.username) : user.username != null)
			return false;
		if (password != null ? !password.equals(user.password) : user.password != null)
			return false;
		if (role != null ? !role.equals(user.role) : user.role != null)
			return false;
		if (img != null ? !img.equals(user.img) : user.img != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (img != null ? img.hashCode() : 0);
		return result;
	}
}
