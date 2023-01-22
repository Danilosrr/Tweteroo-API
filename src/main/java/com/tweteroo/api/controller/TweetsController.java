package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweets;
import com.tweteroo.api.service.TweetsService;

@CrossOrigin
@RestController
@RequestMapping("api/tweets")
public class TweetsController {

    @Autowired
    private TweetsService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createTweet(@RequestHeader("User") String user, @RequestBody TweetDTO req) {
        service.createTweet(user, req);
    }

    @GetMapping
    public Page<Tweets> getTweets(@RequestParam(value = "page", defaultValue = "0") int page) {
        return service.getTweets(page);
    }

    @GetMapping("/{username}")
    public List<Tweets> getUserTweets(@PathVariable String username) {
        return service.getUserTweets(username);
    }
}