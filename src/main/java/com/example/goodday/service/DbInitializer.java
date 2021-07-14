package com.example.goodday.service;

import com.example.goodday.service.dto.UserDto;
import com.example.goodday.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(DbInitializer.class);
    private final com.example.goodday.service.MyUserDetailsService myUserDetailsService;

    @Autowired
    public DbInitializer(com.example.goodday.service.MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public void run(String... args) throws Exception {
        createAdmin();
    }

    private void createAdmin() {
        if (!myUserDetailsService.checkIfAdminExists()){
            UserDto admin = createUser();
            User registered = myUserDetailsService.registerNewUserAccount(admin);
            logger.info("Admin with username:{} and password {} was created",registered.getUserName(),registered.getPassword());
        }
    }

    private UserDto createUser() {
        UserDto user = new UserDto();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setMatchingPassword("admin");
        return user;
    }
}
