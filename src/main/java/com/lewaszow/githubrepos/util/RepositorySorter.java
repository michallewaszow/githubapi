package com.lewaszow.githubrepos.util;

import lombok.extern.slf4j.Slf4j;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.List;

@Slf4j
public class RepositorySorter {

    private static final String ASCENDING = "ascending";
    private static final String DESCENDING = "descending";

    public static List<GithubRepository> sortRepositories(final String sortingMethod, final List<GithubRepository> userRepositories) {
        if (ASCENDING.contains(sortingMethod)) {
            return sortAscending(userRepositories);
        } else if (DESCENDING.contains(sortingMethod)) {
            return sortDescending(userRepositories);
        }
        log.error("Wrong sorting method, define one of [ascending, descending] or substring of one of them.");
        return userRepositories;
    }

    private static List<GithubRepository> sortAscending(final List<GithubRepository> repositories) {
        repositories.sort((r1, r2) -> r1.getUserName()
                                        .compareToIgnoreCase(r2.getUserName()));
        return repositories;
    }

    private static List<GithubRepository> sortDescending(final List<GithubRepository> repositories) {
        repositories.sort((r1, r2) -> r2.getUserName()
                                        .compareToIgnoreCase(r1.getUserName()));
        return repositories;
    }
}
