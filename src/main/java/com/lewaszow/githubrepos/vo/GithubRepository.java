package com.lewaszow.githubrepos.vo;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepository {

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
