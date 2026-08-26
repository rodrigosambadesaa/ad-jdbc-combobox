package dev.rodrigosambade.jdbc;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleRepositoryTest {

    @Test
    void demoDatabaseContainsArticles() throws Exception {
        try (Connection connection = DemoDatabase.open()) {
            ArticleRepository repository = new ArticleRepository();

            assertFalse(repository.findAll(connection).isEmpty());
            assertTrue(repository.findById(connection, 1).isPresent());
        }
    }
}
