package com.hendisantika.springbootrestapipostgresql.http;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/22
 * Time: 06:11
 * To change this template use File | Settings | File Templates.
 */
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
