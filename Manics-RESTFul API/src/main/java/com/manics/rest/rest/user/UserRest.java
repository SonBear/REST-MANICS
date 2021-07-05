package com.manics.rest.rest.user;

import com.manics.rest.mappers.UserMapper;
import com.manics.rest.model.auth.User;
import com.manics.rest.rest.request.user.UserAuthorityRequest;
import com.manics.rest.rest.request.user.UserRequest;
import com.manics.rest.service.user.UserService;
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

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId,
                                           @RequestBody UserRequest request) {

        User user = userService.updateUser(userId, userMapper.userRequestToUser(request));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/authorities/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> updateUserAuthorities(@PathVariable Integer userId,
                                                      @RequestBody @Valid UserAuthorityRequest userAuthorityRequest) {

        User user = userService.updateUserRoles(userId, userAuthorityRequest.getRoles());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
        User user = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }
}