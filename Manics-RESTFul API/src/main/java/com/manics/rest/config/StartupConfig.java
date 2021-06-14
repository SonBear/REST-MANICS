package com.manics.rest.config;

import com.google.common.collect.Sets;
import com.manics.rest.model.auth.User;
import com.manics.rest.model.auth.UserRole;
import com.manics.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String defaultUsername = "admin";
        String defaultPassword = "admin";

        System.out.printf("Default admin username: %s%nDefault admin password: %s%n", defaultUsername, defaultPassword);

        if (!userService.existsUser(defaultUsername))
            userService.createUser(
                    new User(
                            "admin",
                            "admin@gmail.com",
                            defaultPassword,
                            Sets.newHashSet(UserRole.ADMIN)
                    )
            );
    }

}
