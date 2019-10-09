package com.lewaszow.githubrepos.vo;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UserRepository {

    @Getter
    @JsonProperty("name")
    private String userName;
    @JsonProperty("language")
    private String programmingLanguage;
    @JsonProperty("created_at")
    private LocalDateTime creationDate;
    @Getter
    @JsonProperty("updated_at")
    private LocalDateTime lastUpdateDate;
    @Setter
    @Getter
    private boolean recentlyUpdated;

}
