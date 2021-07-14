package com.example.goodday.service;

import com.example.goodday.service.dto.MyUserDetails;
import com.example.goodday.service.dto.UserDto;
import com.example.goodday.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        MyUserDetails myUserDetails = user.map(MyUserDetails::new).get();

        return myUserDetails;
    }

    public User registerNewUserAccount(UserDto userDto) throws com.example.goodday.service.UserAlreadyExistException, IllegalStateException {
        if (userExists(userDto.getUsername())) {
            throw new com.example.goodday.service.UserAlreadyExistException("Użytkownik o podanym loginie istnieje: "
                    + userDto.getUsername());
        }
        if (userDto.getUsername().isEmpty()||userDto.getPassword().isEmpty()||userDto.getMatchingPassword().isEmpty()){
            throw new IllegalStateException("Przekazano puste dane");
        }
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())){
            throw new IllegalStateException("Hasło podane w potwierdzeniu nie zgadza się z hasłem podstawowym");
        }


        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setPassword(hashPassword(userDto.getPassword()));
        user.setActive(true);
        user.setRoles(checkIfAdminExists()?"ROLE_USER":"ROLE_ADMIN");
        return userRepository.save(user);
    }

    public boolean checkIfAdminExists() {
        return userRepository.findByRoles("ROLE_ADMIN").isPresent();
    }

    private boolean userExists(String login) {
        return userRepository.findByUserName(login).isPresent();
    }

    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
