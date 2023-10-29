package com.hendisantika.springbootrestapipostgresql.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/22
 * Time: 07:26
 * To change this template use File | Settings | File Templates.
 */
//@Service
public interface URLShortenerClient2 {
    @POST(value = "generate")
    Call<UrlResponse> getShortURL(@Body UrlRequest request);
}
