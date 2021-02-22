package jmaster.service;

import java.util.List;

import jmaster.model.UserDTO;

public interface UserService {
	void addUser(UserDTO user);

	void updateUser(UserDTO user);

	void deleteUser(int id);

	UserDTO getUserById(int id);

	List<UserDTO> getAllUsers();
}
