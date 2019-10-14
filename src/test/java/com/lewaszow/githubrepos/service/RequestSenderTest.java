package com.lewaszow.githubrepos.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.List;

public class RequestSenderTest {

    @Test
    public void shouldRespondWithStatusOk() {
        // given
        final RequestSender requestSender = new RequestSender();
        // when
        final ResponseEntity<List<GithubRepository>> response = requestSender.getUserGitHubRepositories(
                "michallewaszow");
        // then
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

}
