package com.lewaszow.githubrepos.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.List;

@Service
public class RequestSender {

    public List<GithubRepository> getUserRepositories(final String userName) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<List<GithubRepository>> repositories = restTemplate.exchange("https://api.github.com/users/"
                                                                                          + userName
                                                                                          + "/repos",
                                                                                          HttpMethod.GET,
                                                                                          null,
                                                                                          new ParameterizedTypeReference<List<GithubRepository>>() {
                                                                            });
        return repositories.getBody();
    }
}
