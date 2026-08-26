package dev.rodrigosambade.jdbc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ArticleRepositoryTest {
    @Test void demoContainsArticles() throws Exception {
        try(var c=DemoDatabase.open()){
            var r=new ArticleRepository();
            assertFalse(r.findAll(c).isEmpty());
            assertTrue(r.findById(c,1).isPresent());
        }
    }
}
