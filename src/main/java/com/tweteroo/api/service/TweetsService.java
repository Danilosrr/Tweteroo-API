package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweets;
import com.tweteroo.api.model.Users;
import com.tweteroo.api.repository.TweetsRepository;
import com.tweteroo.api.repository.UsersRepository;

@Service
public class TweetsService {

    @Autowired
    private TweetsRepository tweetsRepository;

    @Autowired
    private UsersRepository userRepository;

    public ResponseEntity<Page<Tweets>> getTweets(int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
        return ResponseEntity.status(HttpStatus.OK).body(tweetsRepository.findAll(pageable));
    }

    public ResponseEntity<List<Tweets>> getUserTweets(@PathVariable String username) {
        List<Tweets> tweets = tweetsRepository.findByUsername(username);
        if (!tweets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(tweets);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<String> createTweet(@RequestHeader("User") String user, @RequestBody TweetDTO req) {
        Tweets tweet = new Tweets(req, user);
        Users avatar = userRepository.findFirstByUsername(user);
        if (avatar == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao fazer tweet!");
        else {
            tweet.setAvatar(avatar.getAvatar());
            tweetsRepository.save(tweet);
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        }
    }
}
