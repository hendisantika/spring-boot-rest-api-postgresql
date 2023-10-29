package com.hendisantika.springbootrestapipostgresql.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponse {
    private String originalUrl;
    private String shortLink;
    private String expirationDate;
}
