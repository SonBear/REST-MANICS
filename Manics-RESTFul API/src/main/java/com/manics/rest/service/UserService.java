package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.exception.UsuarioRegistradoException;
import com.manics.rest.model.auth.User;
import com.manics.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer userId) {
        return userRepo
                .findById(userId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontró el usuario con el id: %d", userId))
                );
    }

    public User createUser(User user) {
        return createUser(user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User createUser(String username, String email, String password) {
        User user = userRepo.findByUsername(username);

        if (!Objects.isNull(user)) throw new UsuarioRegistradoException(username);

        User newUser = new User(username, email, passwordEncoder.encode(password));

        return userRepo.save(newUser);
    }

    public User updateUser(Integer userId, User user) {
        return updateUser(userId, user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User updateUser(Integer userId, String newUsername, String newEmail, String newPassword) {
        User user = getUserById(userId);

        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(passwordEncoder.encode(newPassword));

        return userRepo.save(user);
    }

    public User deleteUser(Integer userId) {
        User user = getUserById(userId);
        userRepo.deleteById(userId);
        return user;
    }

    public void checkIfUserExist(Integer userId) {
        getUserById(userId);
    }

}
