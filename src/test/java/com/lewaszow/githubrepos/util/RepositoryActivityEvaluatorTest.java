package com.lewaszow.githubrepos.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.time.LocalDateTime;

public class RepositoryActivityEvaluatorTest {

    @Test
    public void shouldEvaluateRepositoriesBasedOnActivity() {
        // given
        final int activityThresholdInMonths = RepositoryActivityEvaluator.getActivityThresholdInMonths();
        final GithubRepository activeGitHubRepo = GithubRepository.builder()
                                                                  .lastUpdateDate(LocalDateTime.now())
                                                                  .build();
        final GithubRepository nonActiveGitHubRepo = GithubRepository.builder()
                                                                     .lastUpdateDate(LocalDateTime.now()
                                                                                                  .minusMonths(
                                                                                                          activityThresholdInMonths))
                                                                     .build();
        // when
        RepositoryActivityEvaluator.evaluateActivity(activeGitHubRepo);
        RepositoryActivityEvaluator.evaluateActivity(nonActiveGitHubRepo);
        // then
        Assertions.assertThat(activeGitHubRepo.isRecentlyUpdated()).isEqualTo(true);
        Assertions.assertThat(nonActiveGitHubRepo.isRecentlyUpdated()).isEqualTo(false);
    }
}
