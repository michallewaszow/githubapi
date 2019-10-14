package com.lewaszow.githubrepos.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lewaszow.githubrepos.GithubReposApplication;
import com.lewaszow.githubrepos.service.RequestSender;
import com.lewaszow.githubrepos.test.util.TestResource;
import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GitHubReposController.class, secure = false)
@ContextConfiguration(classes = { GithubReposApplication.class })
public class GitHubReposControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestSender requestSender;

    @Test
    public void shouldRespondWithRepositories() throws Exception {
        // given
        final TestResource testResource = new TestResource();
        final String gitHubReposJson = testResource.getResourceAsString("/gitHubRepos.json", "UTF-8");
        final String gitHubUser = "michallewaszow";
        final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        final List<GithubRepository> gitHubRepos = objectMapper.readValue(gitHubReposJson,
                                                                          new TypeReference<List<GithubRepository>>() {
                                                                          });
        // when
        when(requestSender.getUserGitHubRepositories(gitHubUser)).thenReturn(new ResponseEntity<>(gitHubRepos,
                                                                                                  HttpStatus.OK));
        // then
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/repositories?username=" + gitHubUser))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(content().string(gitHubReposJson))
               .andReturn();
    }
}
