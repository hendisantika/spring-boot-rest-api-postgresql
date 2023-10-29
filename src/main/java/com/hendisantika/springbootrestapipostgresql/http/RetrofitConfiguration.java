package com.hendisantika.springbootrestapipostgresql.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/22
 * Time: 08:30
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Component
public class RetrofitConfiguration {
    @Bean
    public URLShortenerClient2 urlShortenerApiClientConfig() {
        String baseUrl = "http://url-shortener.sit.dev-powerbiz.asia/";
        final Logger logger = LoggerFactory.getLogger(URLShortenerClient2.class);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.MINUTES)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().build();
                    long startTime = System.currentTimeMillis();
                    logger.info("Sending request to url: {}", request.url());
                    Response response = chain.proceed(request);
                    long endTime = System.currentTimeMillis() - startTime;
                    logger.info("Received response for call with duration " + endTime + ":  {}", request.url());
                    return response;
                })
                .build();

        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(URLShortenerClient2.class);
    }
}
