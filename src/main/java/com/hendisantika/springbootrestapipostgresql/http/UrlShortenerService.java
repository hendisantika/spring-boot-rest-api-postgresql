package com.hendisantika.springbootrestapipostgresql.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
@Slf4j
public class UrlShortenerService extends MappingJackson2HttpMessageConverter {
    @Value("${app.services.url-shortener.api-url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private URLShortenerClient2 urlShortenerClient2;

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

    public String getShortUrl(String url) {

        UrlRequest request = new UrlRequest(url);
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
//        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        String shortLink = "";
        try {
            UrlResponse response = restTemplate.postForObject(apiUrl + "/generate", request, UrlResponse.class);
            if (response != null) {
                log.info("URL SHORTENER: ", shortLink);
                shortLink = apiUrl + "/" + response.getShortLink();
            }
        } catch (Exception e) {
            log.error("Error cause: " + e.getMessage());
        }
        return shortLink;
    }

//    public String getShortUrl2(UrlRequest urlRequest) {
//        Result<UrlResponse> shortURL = urlShortenerClient2.getShortURL(urlRequest);
//        return shortURL.toString();
//    }

    public String getShortUrl3(UrlRequest urlRequest) throws Exception {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        URLShortenerClient2 urlShortenerClient2 = retrofit.create(URLShortenerClient2.class);
        UrlResponse urlResponse = urlShortenerClient2.getShortURL(urlRequest).execute().body();
        if (urlResponse != null) {
            log.info("urlResponse.getShortLink() : " + urlResponse.getShortLink());
        }
        return urlResponse.getShortLink();

    }
}
