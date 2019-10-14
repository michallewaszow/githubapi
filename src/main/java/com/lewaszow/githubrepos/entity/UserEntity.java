package com.lewaszow.githubrepos.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @NonNull
    @Column
    private String username;
    @NonNull
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;

}
