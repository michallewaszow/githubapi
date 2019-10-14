package com.lewaszow.githubrepos.util;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryFilterer {

    public static List<GithubRepository> filterRepositoryByActivity(final List<GithubRepository> repositories) {
        return repositories.stream()
                           .filter(GithubRepository::isRecentlyUpdated)
                           .collect(Collectors.toList());
    }
}
