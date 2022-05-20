package learn.fansite.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.fansite.data.FavoriteRepository;
import models.Favorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FavoriteControllerTest {

    @MockBean
    FavoriteRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Favorite favorite = new Favorite(0, null);
        String favoriteJson = jsonMapper.writeValueAsString(favorite);
        
        var request = post("/api/favorite")
                .contentType(MediaType.APPLICATION_JSON)
                .content(favoriteJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn201() throws Exception {
        Favorite favorite = new Favorite(5, "Test Name");
        Favorite expected = new Favorite(6, "Test Name");

        when(repository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();

        String favoriteJson = jsonMapper.writeValueAsString(favorite);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/favorite")
                .contentType(MediaType.APPLICATION_JSON)
                .content(favoriteJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}