package com.lewaszow.githubrepos.util;

import com.lewaszow.githubrepos.vo.GithubRepository;

import java.time.LocalDateTime;

public class RepositoryActivityEvaluator {

    private static int activityThresholdInMonths = 3;

    public static void evaluateActivity(final GithubRepository githubRepository){
        final LocalDateTime lastUpdateDate = githubRepository.getLastUpdateDate();
        if(lastUpdateDate.isAfter(LocalDateTime.now().minusMonths(activityThresholdInMonths))){
            githubRepository.setRecentlyUpdated(true);
        }
    }

}
