package com.lewaszow.githubrepos.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name = "authorities")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "username")
    private UserEntity username;
    @Id
    @Column(name = "authority")
    private String authority;

}
