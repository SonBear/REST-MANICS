package com.manics.rest.rest;

import com.manics.rest.mappers.UserMapper;
import com.manics.rest.model.auth.User;
import com.manics.rest.rest.request.user.UserAuthorityRequest;
import com.manics.rest.rest.request.user.UserRequest;
import com.manics.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UserRest {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserRest(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserRequest request) {
        User user = userService.updateUser(id, userMapper.userRequestToUser(request));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/authorities/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> updateUserAuthorities(@PathVariable(name = "id") Integer userId,
                                                      @RequestBody @Valid UserAuthorityRequest userAuthorityRequest) {

        User user = userService.updateUserRoles(userId, userAuthorityRequest.getRoles());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        User user = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }
}
