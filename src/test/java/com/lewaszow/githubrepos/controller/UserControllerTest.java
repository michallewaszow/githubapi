package com.lewaszow.githubrepos.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lewaszow.githubrepos.GithubReposApplication;
import com.lewaszow.githubrepos.entity.UserEntity;
import com.lewaszow.githubrepos.repository.AuthorityRepository;
import com.lewaszow.githubrepos.repository.UserRepository;
import com.lewaszow.githubrepos.test.util.TestResource;

import javax.activation.DataSource;
import java.util.Optional;

@WebMvcTest(value = UserController.class, secure = false)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GithubReposApplication.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private AuthorityRepository authorityRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldNotCreateUserWithExistingUsername() throws Exception {
        // given
        final String userJson = new TestResource().getResourceAsString("/user.json", "UTF-8");
        final String username = new JSONObject(userJson).getString("username");
        final String password = new JSONObject(userJson).getString("password");

        // when
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new UserEntity(username, password, true)));
        when(passwordEncoder.encode(password)).thenReturn(password);
        userRepository.saveAndFlush(new UserEntity(username, password, true));
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                                              .contentType(MediaType.APPLICATION_JSON_UTF8)
                                              .content(userJson))
               .andExpect(content().string("User with name " + username + " already exists."));
    }
}
