package org.simple.todo.controller;

import org.simple.todo.dto.LoginDTO;
import org.simple.todo.dto.ResponseLoginDTO;
import org.simple.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseLoginDTO login(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
    }
}
