package jmaster.controller.API;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jmaster.model.UserDTO;
import jmaster.service.UserService;

@Controller
public class UserAPIController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/list-user", method = RequestMethod.GET)
	public @ResponseBody List<UserDTO> getAllUsers(HttpServletRequest httpServletRequest) {

		List<UserDTO> allUsers = userService.getAllUsers();
		return allUsers;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody UserDTO viewUser(HttpServletRequest request, @PathVariable(value = "userId") int userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody void addUser(HttpServletRequest httpServletRequest, @RequestBody UserDTO user) {
		userService.addUser(user);
	}

	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable(value = "id") int id) {
		userService.deleteUser(id);
	}
	
	@RequestMapping(value = "/user/update" ,method = RequestMethod.POST)
	public @ResponseBody void updateUser(@RequestBody UserDTO userDTO) {
		userService.updateUser(userDTO);
	}

}
