package com.lewaszow.githubrepos.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lewaszow.githubrepos.service.RequestSender;
import com.lewaszow.githubrepos.util.RepositoryActivityEvaluator;
import com.lewaszow.githubrepos.util.RepositoryFilterer;
import com.lewaszow.githubrepos.util.RepositorySorter;
import com.lewaszow.githubrepos.vo.GithubRepository;

import java.util.List;

@RestController
public class GitHubReposController {

    private RequestSender requestSender;

    @Autowired
    public GitHubReposController(final RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    @GetMapping(path = "/repositories", produces = "application/json")
    public List<GithubRepository> showUserRepos(@RequestParam("username") final String userName,
                                                @RequestParam(value = "showActive", required = false)
                                                final boolean showActive,
                                                @RequestParam(value = "sortByUsername", required = false)
                                                final String sortingMethod) {
        List<GithubRepository> userRepositories = requestSender.getUserGitHubRepositories(userName).getBody();
        userRepositories.forEach(RepositoryActivityEvaluator::evaluateActivity);
        if (showActive) {
            userRepositories = RepositoryFilterer.filterRepositoryByActivity(userRepositories);
        }
        if (StringUtils.isNotBlank(sortingMethod)) {
            userRepositories = RepositorySorter.sortRepositories(sortingMethod, userRepositories);
        }
        return userRepositories;
    }
}
