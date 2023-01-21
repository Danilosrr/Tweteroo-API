package com.tweteroo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweteroo.api.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findFirstByUsername(String username);
}