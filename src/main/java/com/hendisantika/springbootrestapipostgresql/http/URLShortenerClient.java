package com.hendisantika.springbootrestapipostgresql.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/22
 * Time: 05:47
 * To change this template use File | Settings | File Templates.
 */
@FeignClient(value = "generate", url = "https://url-shortener.sit.dev-powerbiz.asia", configuration = FeignConfig.class)
public interface URLShortenerClient {
    @PostMapping(value = "/generate")
    UrlResponse getShortURL(@RequestBody UrlRequest request);
}
