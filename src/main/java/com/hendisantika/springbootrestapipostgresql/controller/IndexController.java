package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.http.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/12/22
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("short")
@Slf4j
public class IndexController {
    @Autowired
    private UrlShortenerService urlShortenerService;

    @Autowired
    private URLShortenerClient urlShortenerClient;


    @GetMapping
    public String shortUrl() {
        return urlShortenerService.getShortUrl("https://s.id/hendisantika");
    }

    @PostMapping
    public String shortUrl(@RequestBody UrlRequest request) {
//        return urlShortenerService.getShortUrl("https://s.id/hendisantika");
        UrlResponse response = urlShortenerClient.getShortURL(request);
        return response.getShortLink();
    }

    @PostMapping("2")
    public Call<UrlResponse> create(@Body UrlRequest request) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://url-shortener.sit.dev-powerbiz.asia/generate/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        URLShortenerClient2 urlShortenerClient21 = retrofit.create(URLShortenerClient2.class);

        Call<UrlResponse> callSync = urlShortenerClient21.getShortURL(request);

        try {
            Response<UrlResponse> response = callSync.execute();
            UrlResponse apiResponse = response.body();
            log.info("apiResponse: " + apiResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return callSync;
    }

    @PostMapping("3")
    public String shortUrl3(@RequestBody UrlRequest request) throws Exception {
//        return urlShortenerService.getShortUrl("https://s.id/hendisantika");
        return urlShortenerService.getShortUrl3(request);
    }

}
