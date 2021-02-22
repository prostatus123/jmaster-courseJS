package jmaster.service.impl;

import jmaster.dao.UserDao;
import jmaster.entity.User;
import jmaster.model.UserDTO;
import jmaster.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setImg(userDTO.getImg());

        userDao.add(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
       User user = userDao.get(userDTO.getId());
       if(user != null){
           user.setUsername(userDTO.getUsername());
           user.setPassword(userDTO.getPassword());
           user.setRole(userDTO.getRole());
           user.setImg(userDTO.getImg());
           userDao.update(user);
       }
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.get(id);
        if(user != null){
            userDao.delete(id);
        }
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userDao.get(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setImg(user.getImg());

        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<User> users = userDao.getAll();
       List<UserDTO> userDTOS = new ArrayList<UserDTO>();
       for(User user : users){
           UserDTO userDTO = new UserDTO();

           userDTO.setId(user.getId());
           userDTO.setUsername(user.getUsername());
           userDTO.setPassword(user.getPassword());
           userDTO.setRole(user.getRole());
           userDTO.setImg(user.getImg());
           userDTOS.add(userDTO);
       }
       return userDTOS;
    }
}
