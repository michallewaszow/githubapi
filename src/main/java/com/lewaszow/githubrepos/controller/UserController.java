package com.lewaszow.githubrepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lewaszow.githubrepos.entity.AuthorityEntity;
import com.lewaszow.githubrepos.entity.UserEntity;
import com.lewaszow.githubrepos.repository.AuthorityRepository;
import com.lewaszow.githubrepos.repository.UserRepository;
import com.lewaszow.githubrepos.vo.UserDetails;

@RestController
public class UserController {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(final UserRepository userRepository, final AuthorityRepository authorityRepository, final
                          PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String addUser(@RequestBody UserDetails userDetails){
        final String username = userDetails.getUsername();
        final String password = userDetails.getPassword();
        final String role = userDetails.getRole();
        if(userRepository.findByUsername(username).isPresent()){
            return "User with name " + username + " already exists.";
        } else {
            userRepository.saveAndFlush(new UserEntity(username, passwordEncoder.encode(password), true));
            authorityRepository.saveAndFlush(new AuthorityEntity(userRepository.findByUsername(username).get(), role));
            return "Successfully added User with username = " + username;
        }
    }

}
