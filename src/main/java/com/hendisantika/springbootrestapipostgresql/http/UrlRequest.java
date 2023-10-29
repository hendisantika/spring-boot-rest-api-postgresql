package com.hendisantika.springbootrestapipostgresql.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UrlRequest {

    private String url;

    @JsonCreator
    public UrlRequest(@JsonProperty("url") String url) {
        this.url = url;
    }
}
