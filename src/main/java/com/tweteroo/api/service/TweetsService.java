package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweets;
import com.tweteroo.api.repository.TweetsRepository;
import com.tweteroo.api.repository.UsersRepository;

@Service
public class TweetsService {

    @Autowired
    private TweetsRepository tweetsRepository;

    @Autowired
    private UsersRepository userRepository;

    public List<Tweets> getTweets() {
        return tweetsRepository.findAll();
    }

    public List<Tweets> getUserTweets(@PathVariable String username) {
        return tweetsRepository.findByUsername(username);
    }

    public void createTweet(@RequestHeader("User") String user, @RequestBody TweetDTO req) {
        Tweets tweet = new Tweets(req, user);
        String avatar = userRepository.findFirstByUsername(user).getAvatar();
        tweet.setAvatar(avatar);
        tweetsRepository.save(tweet);
    }
}