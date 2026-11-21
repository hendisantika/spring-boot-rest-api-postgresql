package com.hendisantika.springbootrestapipostgresql;

import com.hendisantika.springbootrestapipostgresql.entity.Book;
import com.hendisantika.springbootrestapipostgresql.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringBootRestApiPostgresqlApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldSaveAndFindBookByName() {
        bookRepository.save(new Book("Naruto", "Shinobi story", List.of("manga", "ninja")));

        List<Book> books = bookRepository.findByName("Naruto");

        assertThat(books).hasSize(1);
        assertThat(books.getFirst().getId()).isNotNull();
        assertThat(books.getFirst().getTags()).containsExactly("manga", "ninja");
    }
}
