package com.tweteroo.api.model;

import com.tweteroo.api.dto.TweetDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Tweets {

    public Tweets(TweetDTO data, String user) {
        this.username = user;
        this.text = data.text();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 400, nullable = true)
    private String text;

    @Column(length = 100, nullable = true)
    private String avatar;
}
