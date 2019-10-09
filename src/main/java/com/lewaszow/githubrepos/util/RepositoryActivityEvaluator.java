package com.lewaszow.githubrepos.util;

import com.lewaszow.githubrepos.vo.UserRepository;

import java.time.LocalDateTime;

public class RepositoryActivityEvaluator {

    private static int activityThresholdInMonths = 3;

    public static void evaluateActivity(final UserRepository userRepository){
        final LocalDateTime lastUpdateDate = userRepository.getLastUpdateDate();
        if(lastUpdateDate.isAfter(LocalDateTime.now().minusMonths(activityThresholdInMonths))){
            userRepository.setRecentlyUpdated(true);
        }
    }

}
