package com.hendisantika.springbootrestapipostgresql.http;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@Configuration
public class HTTPClientConfig {

    @Value("${app.services.url-shortener.api-url}")
    private String apiUrl;

    @Bean
    public RestTemplate restTemplate() {
        var factory = new SimpleClientHttpRequestFactory();
        return new RestTemplate(factory);
    }
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        // Get the converter list first
//        List<HttpMessageConverter<?>> converters = builder.build().getMessageConverters();
//        for (HttpMessageConverter<?> converter : converters) {
//            //Because we only want jsonConverter to support parsing of text/html
//            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                try {
//                    // First copy the original supported MediaType list
//                    List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
//                    //Add support for text/html
//                    mediaTypeList.add(MediaType.TEXT_HTML);
//                    //Set the MediaType support list that has been added to text/html to the list of supported media
//                    // types.
//                    ((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(mediaTypeList);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return builder.build();
//    }
//public class MyGsonHttpMessageConverter extends GsonHttpMessageConverter {
//    public MyGsonHttpMessageConverter() {
//        List<MediaType> types = Arrays.asList(
//                new MediaType("text", "html", DEFAULT_CHARSET),
//                new MediaType("application", "json", DEFAULT_CHARSET),
//                new MediaType("application", "*+json", DEFAULT_CHARSET)
//        );
//        super.setSupportedMediaTypes(types);
//    }
//}

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        List<MediaType> supportedMediaTypes = converter.getSupportedMediaTypes();
        if (!supportedMediaTypes.contains(TEXT_PLAIN)) {
            supportedMediaTypes = new ArrayList<>(supportedMediaTypes);
            supportedMediaTypes.add(TEXT_PLAIN);
            converter.setSupportedMediaTypes(supportedMediaTypes);
        }
        return converter;
    }

//    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//    @Bean
//    public Retrofit retrofit() {
//        return new Retrofit.Builder()
//                .baseUrl(apiUrl)
//                .client(clientBuilder.build())
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build();
//    }
//    @Autowired
//    private Retrofit retrofit;
//    private URLShortenerClient2 urlShortenerClient2;
//
//    @PostConstruct
//    public void setup() {
//        urlShortenerClient2 = retrofit.create(URLShortenerClient2.class);
//    }
}
