package com.lewaszow.githubrepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lewaszow.githubrepos.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
