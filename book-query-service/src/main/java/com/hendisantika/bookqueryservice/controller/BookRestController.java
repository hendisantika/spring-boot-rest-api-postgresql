package com.hendisantika.bookqueryservice.controller;

import com.hendisantika.bookqueryservice.entity.Book;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-18
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private RestTemplate restTemplate;

    private final String BOOK_MANAGE_SERVICE_URL = "http://localhost:8080/api/books";


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookWithId(@PathVariable Long id) {
        String url = BOOK_MANAGE_SERVICE_URL + "/" + id;
        ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Collection<Book>> findBookWithName(@RequestParam(value = "name") String name) {
//        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
        String url = BOOK_MANAGE_SERVICE_URL + "?name=" + name;
        ResponseEntity<Book[]> response = restTemplate.getForEntity(url, Book[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Book> books = Arrays.asList(response.getBody());
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}