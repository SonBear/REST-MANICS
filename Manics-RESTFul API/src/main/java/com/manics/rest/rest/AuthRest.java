package com.manics.rest.rest;

import com.manics.rest.mappers.UserMapper;
import com.manics.rest.model.auth.User;
import com.manics.rest.rest.request.user.UserRequest;
import com.manics.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AuthRest {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AuthRest(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequest request) throws URISyntaxException {
        User user = userService.createUser(userMapper.userRequestToUser(request));

        return ResponseEntity
                .created(new URI("/usuarios/" + user.getUserId()))
                .body(user);
    }

}
