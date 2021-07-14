package com.example.goodday.controller;

import com.example.goodday.service.MyUserDetailsService;
import com.example.goodday.service.UserAlreadyExistException;
import com.example.goodday.service.dto.UserDto;
import com.example.goodday.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public MainController(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/showDailyTask")
    public String showDailyTask(){
        return "Pozmywać okna";
    }

    @PostMapping("/register")
    public String registerUserAccount(@RequestBody UserDto userDto) {
        try {
            User registered = myUserDetailsService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException | IllegalStateException uaeEx) {
            return uaeEx.getMessage();
        }
        return "Użytkownik został zarejestrowany";
    }

//    @PostMapping("/addTask")
//    public String addTask(@RequestBody DailyTaskDto dailytaskDto) {
//        try {
//            Task added =  myUserDetailsService.registerNewUserAccount(userDto);
//        } catch (UserAlreadyExistException | IllegalStateException uaeEx) {
//            return uaeEx.getMessage();
//        }
//        return "Użytkownik został zarejestrowany";
//    }
//    

}

