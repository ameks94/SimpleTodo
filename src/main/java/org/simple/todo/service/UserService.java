package org.simple.todo.service;

import org.simple.todo.dto.ResponseLoginDTO;
import org.simple.todo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllStoredUsers();

    UserDTO saveUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO findUser(int id);

    void deleteUser(int id);

    ResponseLoginDTO loginUser(String username, String password);

}
