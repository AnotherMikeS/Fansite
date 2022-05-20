package learn.fansite.controllers;

import learn.fansite.domain.FavoriteService;
import learn.fansite.domain.Result;
import models.Favorite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public List<Favorite> findAll() {
        return favoriteService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Favorite favorite){
        Result<Favorite> result = favoriteService.add(favorite);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{position}")
    public ResponseEntity<Void> deleteByPosition(@PathVariable int position) {
        if (favoriteService.deleteByPosition(position)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
