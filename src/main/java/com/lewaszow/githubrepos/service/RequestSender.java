package com.lewaszow.githubrepos.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lewaszow.githubrepos.vo.UserRepository;

import java.util.List;

@Service
public class RequestSender {

    public List<UserRepository> getUserRepositories(final String userName) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<List<UserRepository>> repositories = restTemplate.exchange("https://api.github.com/users/"
                                                                                        + userName
                                                                                        + "/repos",
                                                                                        HttpMethod.GET,
                                                                                        null,
                                                                                        new ParameterizedTypeReference<List<UserRepository>>() {
                                                                            });
        return repositories.getBody();
    }
}
