package org.simple.todo.repository;

import org.simple.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByUsername(String username);
    List<User> findByUsernameAndPasswordAndEnabled(String username, String password, Boolean enabled);

}
