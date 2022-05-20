package learn.fansite.domain;

import learn.fansite.data.FavoriteRepository;
import models.Favorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

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
        favorite = new Favorite(10, "");
        result = service.add(favorite);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(result.getMessages().get(0), "name cannot be empty");
    }

    @Test
    void shouldNotAddDuplicate(){
        Favorite existingFavorite = makeFavorite();
        repository.add(existingFavorite);
        List<Favorite> pls = new ArrayList<>();
        pls.add(existingFavorite);

        when(repository.findAll()).thenReturn(pls);

        //Duplicate ID
        Favorite favorite = new Favorite(37, "testName");
        Result<Favorite> result = service.add(favorite);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(result.getMessages().get(0), "position cannot be duplicate");

        //Duplicate Name
        favorite = new Favorite(10, "Steve");
        result = service.add(favorite);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(result.getMessages().get(0), "name cannot be duplicate");
    }

    private Favorite makeFavorite(){
        return new Favorite(37, "Steve");
    }
}