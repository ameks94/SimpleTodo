package org.simple.todo.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.simple.todo.dto.UserDTO;
import org.simple.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public UserDTO getUser(@RequestParam int id) {
        return userService.findUser(id);
    }

    @RequestMapping(method = POST)
    public UserDTO saveUser(@RequestBody UserDTO user) {
        return userService.saveUser(user);
    }

    @RequestMapping(method = PUT)
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

    @RequestMapping(path="/{id}", method = DELETE)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
