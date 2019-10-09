package com.lewaszow.githubrepos.util;

import com.lewaszow.githubrepos.vo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryFilterer {

    public static List<UserRepository> filterRepositoryByActivity(final List<UserRepository> repositories) {
        return repositories.stream()
                           .filter(UserRepository::isRecentlyUpdated)
                           .collect(Collectors.toList());
    }
}
