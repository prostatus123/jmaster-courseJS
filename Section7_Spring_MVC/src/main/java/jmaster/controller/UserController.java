package jmaster.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jmaster.model.UserDTO;
import jmaster.service.UserService;
import jmaster.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(HttpServletRequest httpServletRequest){

        httpServletRequest.setAttribute("user",new UserDTO());
        return "addUser";
    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addUser(HttpServletRequest httpServletRequest, @ModelAttribute(value="user") UserDTO user
//            , BindingResult bindingResult) throws IOException {
//        userValidator.validate(user,bindingResult);
//        if(bindingResult.hasErrors()){
//            return "addUser";
//        }
//        MultipartFile file = user.getAvatar();
//        File newFile = new File("C:\\Users\\Admin\\Desktop\\class-spring08\\Spring\\src\\main\\webapp\\resources\\image\\"
//                + file.getOriginalFilename());
//        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//        fileOutputStream.write(file.getBytes());
//        fileOutputStream.close();
//
//        user.setImg(file.getOriginalFilename());
//
//        userService.addUser(user);
//        return "redirect:/user/list-user";
//    }

//    @RequestMapping(value = "/view-users", method = RequestMethod.GET)
//    public @ResponseBody List<UserDTO> viewUsers(HttpServletRequest httpServletRequest){
//        List<UserDTO> allUsers = userService.getAllUsers();
//        return allUsers;
//    }

    @RequestMapping(value = "/list-user", method = RequestMethod.GET)
    public String getAllUsers(HttpServletRequest httpServletRequest){

        List<UserDTO> allUsers = userService.getAllUsers();
        httpServletRequest.setAttribute("allUsers",allUsers);
        return "viewUser";
    }

    @RequestMapping(value = "/detail/{userId}", method = RequestMethod.GET)
    public String inDetail(HttpServletRequest request, @PathVariable(value="userId")int userId){

        request.setAttribute("user", userService.getUserById(userId));
        return "viewUserInDetail";
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUser(HttpServletRequest request, @PathVariable(value="userId")int userId){
        request.setAttribute("user",userService.getUserById(userId));
        return "editUser";
    }

//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String editUser(HttpServletRequest httpServletRequest, @ModelAttribute(value="user") UserDTO user
//            , BindingResult bindingResult) throws IOException {
//        userValidator.validate(user,bindingResult);
//        if(bindingResult.hasErrors()){
//            return "editUser";
//        }
//        MultipartFile file = user.getAvatar();
//        File newFile = new File("C:\\Users\\Admin\\Desktop\\class-spring08\\Spring\\src\\main\\webapp\\resources\\image\\"
//                + file.getOriginalFilename());
//        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//        fileOutputStream.write(file.getBytes());
//        fileOutputStream.close();
//
//        user.setImg(file.getOriginalFilename());
//        userService.updateUser(user);
//        return "redirect:/user/list-user";
//    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(HttpServletRequest request, @PathVariable(value="userId")int userId){

        userService.deleteUser(userId);
        return "redirect:/user/list-user";
    }

}
