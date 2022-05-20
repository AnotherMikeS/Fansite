package learn.fansite.domain;

import learn.fansite.data.FavoriteRepository;
import models.Favorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static learn.fansite.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FavoriteServiceTest {

    @Autowired
    FavoriteService service;

    @MockBean
    FavoriteRepository repository;

    @Test
    void shouldAdd(){
        Favorite toReturn = makeFavorite();
        when(repository.add(any())).thenReturn(toReturn);

        Result expected = makeResult();
        expected.setPayload(makeFavorite());

        Result actual = service.add(makeFavorite());
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAdd(){
        //null favorite
        Result<Favorite> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());

        //invalid position, valid name
        Favorite favorite = new Favorite(0, "test");
        result = service.add(favorite);
        assertEquals(ResultType.INVALID, result.getType());

        //Valid position, null name
        favorite = new Favorite(1, null);
        result = service.add(favorite);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("name cannot be null", result.getMessages().get(0));

        //Valid position, invalid name
        favorite = new Favorite(1, "");
        result = service.add(favorite);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(result.getMessages().get(0), "name cannot be empty");
    }

    private Favorite makeFavorite(){
        return new Favorite(37, "Steve");
    }
}