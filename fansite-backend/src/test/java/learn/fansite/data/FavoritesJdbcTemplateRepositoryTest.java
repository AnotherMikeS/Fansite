package learn.fansite.data;

import models.Favorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FavoritesJdbcTemplateRepositoryTest {

    @Autowired
    FavoriteJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Favorite> favorites = repository.findAll();
        assertNotNull(favorites);
    }

    @Test
    void shouldAdd(){
        Favorite favorite = makeFavorite();
        Favorite actual = repository.add(favorite);
        assertNotNull(actual);
        assertEquals(5, repository.findAll().size());
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteByPosition(1));
        assertFalse(repository.deleteByPosition(64));
    }

    private Favorite makeFavorite(){
        return new Favorite(37, "Steve");
    }
}