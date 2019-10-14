package com.lewaszow.githubrepos.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.Arrays;
import java.util.List;

public class RepositoryFiltererTest {

    @Test
    public void shouldFilterRepositoryByActivity() {
        // given
        final GithubRepository recentlyUpdatedRepository = GithubRepository.builder()
                                                       .recentlyUpdated(true)
                                                       .build();
        final GithubRepository notUpdatedRepository = GithubRepository.builder()
                                                       .recentlyUpdated(false)
                                                       .build();
        // when
        final List<GithubRepository> filteredGithubRepositories = RepositoryFilterer.filterRepositoryByActivity(Arrays.asList(
                recentlyUpdatedRepository,
                notUpdatedRepository));
        // then
        Assertions.assertThat(filteredGithubRepositories).containsExactly(recentlyUpdatedRepository);
    }

}
