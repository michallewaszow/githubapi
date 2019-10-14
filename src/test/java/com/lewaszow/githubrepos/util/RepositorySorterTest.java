package com.lewaszow.githubrepos.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.Arrays;
import java.util.List;

public class RepositorySorterTest {

    @Test
    public void shouldSortRepositoriesCorrectly() {
        // given
        final String firstInAlphabeticOrder = "aaa";
        final String secondInAlphabeticOrder = "bbb";
        final GithubRepository repoWithFirstUsername = GithubRepository.builder()
                                                                       .userName(firstInAlphabeticOrder)
                                                                       .build();
        final GithubRepository repoWithSecondUsername = GithubRepository.builder()
                                                                        .userName(secondInAlphabeticOrder)
                                                                        .build();
        final List<GithubRepository> githubRepositories = Arrays.asList(repoWithFirstUsername, repoWithSecondUsername);
        // when & then
        final List<GithubRepository> sortedAscending = RepositorySorter.sortRepositories("ascending",
                                                                                         githubRepositories);
        Assertions.assertThat(sortedAscending)
                  .containsExactly(repoWithFirstUsername, repoWithSecondUsername);

        final List<GithubRepository> sortedDescending = RepositorySorter.sortRepositories("descending",
                                                                                          githubRepositories);
        Assertions.assertThat(sortedDescending)
                  .containsExactly(repoWithSecondUsername, repoWithFirstUsername);
    }
}
