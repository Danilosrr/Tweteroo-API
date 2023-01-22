package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Tweets> getTweets(int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
        return tweetsRepository.findAll(pageable);
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
