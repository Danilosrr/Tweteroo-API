package com.tweteroo.api.model;

import java.util.List;

public class Response {
    public List<Tweets> content;

    public Response(List<Tweets> data) {
        this.content = data;
    }

    Object obj() {
        return this;
    }
}
