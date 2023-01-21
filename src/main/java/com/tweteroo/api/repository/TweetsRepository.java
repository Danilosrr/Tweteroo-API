package com.tweteroo.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweteroo.api.model.Tweet;

public interface TweetsRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUsername(String username);
}
