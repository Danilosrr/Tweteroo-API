package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.repository.TweetsRepository;

@RestController
@RequestMapping("/tweets")
public class TweetsController {

    @Autowired
    private TweetsRepository repository;

    @PostMapping
    public void createTweet(@RequestBody TweetDTO req) {
        repository.save(new Tweet(req));
    }

    @GetMapping
    public List<Tweet> getTweets() {
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public List<Tweet> getUserTweets(@PathVariable String username) {
        return repository.findByUsername(username);
    }
}